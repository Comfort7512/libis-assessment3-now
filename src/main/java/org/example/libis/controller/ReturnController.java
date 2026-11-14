package org.example.libis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.libis.dao.MemberDAO;
import org.example.libis.dao.MemberDAOImpl;
import org.example.libis.exeception.LoanException;
import org.example.libis.model.Member;
import org.example.libis.service.LoanServiceImpl;
import org.example.libis.service.LoanServiceInterface;

import java.io.IOException;


@WebServlet("/return-controller")
public class ReturnController
        extends HttpServlet {

    LoanServiceInterface loanService =
            new LoanServiceImpl();

    MemberDAO memberDAO =
            new MemberDAOImpl();

    @Override
    protected void doPost(
            HttpServletRequest req,
            HttpServletResponse resp)
            throws ServletException,
            IOException {

        String username =
                req.getRemoteUser();

        Member member =
                memberDAO.searchByEmail(
                        username
                );

        int memberId =
                member.getMember_id();

        int bookId =
                Integer.parseInt(
                        req.getParameter("bookId")
                );

        try {

            loanService.returnBook(
                    memberId,
                    bookId
            );

            resp.sendRedirect(
                    req.getContextPath()
                            + "/my-loans"
            );

        } catch (LoanException e) {

            req.getSession().setAttribute(
                    "error",
                    e.getMessage()
            );

            resp.sendRedirect(
                    req.getContextPath()
                            + "/my-loans"
            );
        }
    }
}
