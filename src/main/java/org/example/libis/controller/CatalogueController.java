package org.example.libis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.libis.dao.BookDAOImpl;
import org.example.libis.model.Book;

import java.io.IOException;

@WebServlet(name = "catalogue", value = "/catalogue-controller")
public class CatalogueController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        BookDAOImpl bookDAOImpl = new BookDAOImpl();

//        List<Book> bookList =
//                (List<Book>) getServletContext().getAttribute("books");



        String searchTitle = req.getParameter("search");

        if (searchTitle == null || searchTitle.isBlank()) {
            req.setAttribute("searchPerformed", false);
        } else {
            boolean foundBook = false;

            Book book = bookDAOImpl.searchByTitle(searchTitle);

            if(book != null ){
            req.setAttribute("book", book);
            foundBook = true;
            req.setAttribute("foundBook", foundBook);


            }else {
                req.setAttribute("searchPerformed", true);


            }


        }

        req.getRequestDispatcher("/WEB-INF/Welcome.jsp").forward(req, resp);
    }
}
