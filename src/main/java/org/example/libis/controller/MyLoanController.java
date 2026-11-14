package org.example.libis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.libis.dao.LoanDAO;
import org.example.libis.dao.LoanDAOImpl;
import org.example.libis.dao.MemberDAO;
import org.example.libis.dao.MemberDAOImpl;
import org.example.libis.model.Member;

import java.io.IOException;

@WebServlet("/my-loans")
public class MyLoanController
        extends HttpServlet {

    LoanDAO loanDAO =
            new LoanDAOImpl();

    MemberDAO memberDAO =
            new MemberDAOImpl();

    @Override
    protected void doGet(
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

        req.setAttribute(
                "openLoans",
                loanDAO.findOpenByMember(
                        memberId
                )
        );

        req.setAttribute(
                "history",
                loanDAO.findHistoryByMember(
                        memberId
                )
        );

        req.setAttribute(
                "loanCount",
                loanDAO.countOpenByMember(
                        memberId
                )
        );

        req.getRequestDispatcher(
                "/WEB-INF/my-loans.jsp"
        ).forward(req, resp);
    }
}