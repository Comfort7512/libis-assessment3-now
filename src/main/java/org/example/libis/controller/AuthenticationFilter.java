package org.example.libis.controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {
        "/Book.jsp", "/Welcome.jsp"
})
public class AuthenticationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request1 = (HttpServletRequest) request;
        HttpServletResponse response1 = (HttpServletResponse) response;

        HttpSession session = request1.getSession(false);

        if(session==null || session.getAttribute("username") ==null){
            response1.sendRedirect(request1.getContextPath()+"login.jsp");
            return;
        }

        chain.doFilter(request,response);

    }
}
