package org.example.libis;

import com.mysql.cj.Session;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.libis.data.UserDAO;
import org.example.libis.model.Member;
import org.example.libis.model.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "login-controller", value = "/login")
public class LoginServlet extends HttpServlet {
    User foundUser = null;
    UserDAO userDAO = new UserDAO();

    private static int nextMemberId = 6;


    List<Member> members = new ArrayList<>();



    @Override
    public void init() {
        Member member1 = new Member(1,"Henry","Kago","hen@gmail.com","password1234");
        Member member2 = new Member(2,"Kgotso","Theo","kgo@gmail.com","password123");
        Member member3 = new Member(3,"John","John","john@gmail.com","password12");
        Member member4 = new Member(4,"Kisto","Mothusi","kitso@gmail.com","password1");
        Member member5 = new Member(5,"Dayana","Motuti","dayanan@gmail.com","password");
        members.add(member1);
        members.add(member2);
        members.add(member3);
        members.add(member4);
        members.add(member5);

    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/login.jsp")
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

        foundUser = userDAO.validate(email,password);
        System.out.println(foundUser);


        if(foundUser != null){
            session.setAttribute("username",foundUser.getName());
            req.getRequestDispatcher("/WEB-INF/Welcome.jsp")
                    .forward(req, resp);
        }
        if(foundUser == null){
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

        user = userDAO.registerUser(
                   email,
                   password,
                   firstName,
                   lastName
        );
        if(user>0){
            session.setAttribute("username",firstName);
            request.getRequestDispatcher("/WEB-INF/Welcome.jsp")
                    .forward(request, response);
        }




//        Member member = new Member(
//                nextMemberId++,
//                firstName,
//                lastName,
//                email,
//                password
//        );
//
//        members.add(member);
//        request.getRequestDispatcher("/WEB-INF/Welcome.jsp")
//                .forward(request, response);
//        System.out.println(member);
    }
}
