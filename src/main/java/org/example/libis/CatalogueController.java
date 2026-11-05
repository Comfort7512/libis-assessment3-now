package org.example.libis;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.libis.model.Book;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "catalogue", value = "/catalogue-controller")
public class CatalogueController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Book> bookList =
                (List<Book>) getServletContext().getAttribute("books");

        String searchTitle = req.getParameter("search");

        if (searchTitle == null || searchTitle.isBlank()) {
            req.setAttribute("searchPerformed", false);
        } else {
            boolean foundBook = false;

            for (Book book : bookList) {
                if (book.getTitle().toLowerCase()
                        .contains(searchTitle.toLowerCase())) {

                    req.setAttribute("book", book);
                    foundBook = true;
                    break;
                }
            }

            req.setAttribute("searchPerformed", true);
            req.setAttribute("foundBook", foundBook);
        }

        req.getRequestDispatcher("/WEB-INF/Welcome.jsp").forward(req, resp);
    }
}
