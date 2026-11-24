package org.example.libis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.libis.dao.MemberDAO;
import org.example.libis.dao.MemberDAOImpl;
import org.example.libis.model.Member;
import org.example.libis.model.Role;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/member-controller")
public class MemberController extends HttpServlet {

    MemberDAO memberDAO = new MemberDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Member member ;

        String email = req.getRemoteUser();
        Member member1 = memberDAO.searchByEmail(email);
        //System.out.println("Member username \n"+member1.getUsername());

        //boolean searchedPerformed = false;
        String searchType = req.getParameter("searchType");
        String keyword = req.getParameter("keyword");

        String action = req.getParameter("action");

        if ("edit".equalsIgnoreCase(action)) {

            int memberId = Integer.parseInt(
                    req.getParameter("memberId")
            );

             member = memberDAO.findById(memberId);

            req.setAttribute("member", member);
            req.setAttribute("searchPerformed", true);

            req.getRequestDispatcher("/WEB-INF/member-list.jsp")
                    .forward(req, resp);

            return;
        }

        if(searchType !=null && keyword!=null){
            switch (searchType){
                case "id" :

                    try {
                        int id = Integer.parseInt(keyword);
                        member = memberDAO.findById(id);
                        req.setAttribute("searchPerformed",true);
                        req.setAttribute("member",member);
                        req.getRequestDispatcher("/WEB-INF/member-list.jsp").forward(req,resp);

                    }catch (NumberFormatException e){
                        req.setAttribute("Error","");
                    }


                    break;

                case "username" :
                    member = memberDAO.searchByUsername(keyword);
                    req.setAttribute("searchPerformed",true);
                    req.setAttribute("member",member);
                    req.getRequestDispatcher("/WEB-INF/member-list.jsp").forward(req,resp);
                    log("Search by username called");
                    System.out.println("Search by username called");
                    break;
                case "memberNumber" :
                   member = memberDAO.searchByMemberNumber(keyword);
                    req.setAttribute("searchPerformed",true);
                    req.setAttribute("member",member);
                    req.getRequestDispatcher("/WEB-INF/member-list.jsp").forward(req,resp);
                    break;

                case "email" :
                    member = memberDAO.searchByEmail(keyword);
                    req.setAttribute("searchPerformed",true);
                    req.setAttribute("member",member);
                    req.getRequestDispatcher("/WEB-INF/member-list.jsp").forward(req,resp);
                    break;


                default: req.setAttribute("error","Invalid Search");
            }
        }else {
            req.setAttribute("members", memberDAO.findAll());
            req.getRequestDispatcher("WEB-INF/member-list.jsp").forward(req,resp);


        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String rolePar = req.getParameter("role");
        String memberNumber = req.getParameter("memberNumber");

        Role role = rolePar != null ? Role.valueOf(rolePar.toUpperCase()) : null;


        if (action != null && action.equalsIgnoreCase("delete")) {
            int id = Integer.parseInt(req.getParameter("memberId"));
            memberDAO.delete(id);


            resp.sendRedirect(req.getContextPath() + "/member-controller");
            return;
        }


        if ("update".equalsIgnoreCase(action)) {

            Member member = new Member();

            member.setMember_id(
                    Integer.parseInt(req.getParameter("memberId")));

            member.setMember_number(
                    req.getParameter("memberNumber"));

            member.setName(
                    req.getParameter("name"));

            member.setSurname(
                    req.getParameter("surname"));

            member.setEmail(
                    req.getParameter("email"));

            member.setUsername(
                    req.getParameter("username"));

            member.setPassword(
                    req.getParameter("password"));

            member.setRole(
                    Role.valueOf(req.getParameter("role")));

            memberDAO.update(member);

            resp.sendRedirect(
                    req.getContextPath() + "/member-controller");

            return;
        }

        int checkFailedOrSuccess = memberDAO.create(new Member(
                firstName,
                lastName,
                email,
                username,
                password,
                role
        ));

        if(checkFailedOrSuccess > 0){
            req.setAttribute("success",true);
            req.getRequestDispatcher("/fragments/messages.jsp").forward(req,resp);
        }
        if(action.equalsIgnoreCase("delete")){
            int id = Integer.parseInt(req.getParameter("memberId"));

            memberDAO.delete(id);

            resp.sendRedirect("/member-controller");
            return;
        }









    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }
}
