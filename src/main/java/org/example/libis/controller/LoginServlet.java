package org.example.libis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.libis.dao.MemberDAOImpl;
import org.example.libis.model.Member;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "login-controller", value = "/login")
public class LoginServlet extends HttpServlet {
    Member foundMember = null;
    MemberDAOImpl memberDAO = new MemberDAOImpl();




    List<Member> members = new ArrayList<>();





    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/login.jsp")
                .forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");


        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String action = req.getParameter("action");

        switch (action){
            case "login":
                login(email,password,req,resp);
                System.out.println("Call Login");
                break;
            case "register":
                registerUser(email,password,firstName,lastName,resp,req);
                System.out.println("Call register");
                break;
            default:
                System.out.println("Wrong action");
        }

    }

    public void login
            (String email,
             String password,
             HttpServletRequest req,
             HttpServletResponse resp ) throws ServletException, IOException {

        HttpSession session = req.getSession();
        boolean valid = false;

        Member members1 = null;
//        members1 = memberDAO.searchByEmail("kitso@gmail.com");
//
//        System.out.println("Members found\n" +members1);

        foundMember = memberDAO.validate(email,password);


        if(foundMember != null){
            session.setAttribute("username", foundMember.getName());
//            req.getRequestDispatcher("/WEB-INF/Welcome.jsp")
//                    .forward(req, resp);
            resp.sendRedirect(req.getContextPath() + "/book-controller");

        }
        if(foundMember == null){
            req.getRequestDispatcher("/WEB-INF/login.jsp")
                    .forward(req, resp);
//            resp.sendRedirect(req.getContextPath() + "/login.jsp");

        }

    }

    public void registerUser
            (String email,
             String password,
             String firstName,
             String lastName,
             HttpServletResponse response,
             HttpServletRequest request
             ) throws ServletException, IOException {

        int user = -1;
        HttpSession session = request.getSession();

        user = memberDAO.registerUser(
                email,
                password,
                firstName,
                lastName
        );
        if (user > 0) {
            session.setAttribute("username", firstName);
            request.getRequestDispatcher("/WEB-INF/Welcome.jsp")
                    .forward(request, response);
        }


    }
}
