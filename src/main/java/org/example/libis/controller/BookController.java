package org.example.libis.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.libis.dao.BookDAOImpl;
import org.example.libis.dao.LoanDAO;
import org.example.libis.dao.LoanDAOImpl;
import org.example.libis.model.Book;
import org.example.libis.model.Loan;
import org.example.libis.model.Member;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "book-view", value = "/book-controller", loadOnStartup = 1)
public class BookController extends HttpServlet {

    BookDAOImpl bookDAOImpl = new BookDAOImpl();
    LoanDAO loanDAO = new LoanDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        String bookId = req.getParameter("bookId");


        if(bookId==null){
            List<Book> books = bookDAOImpl.findAll();
            System.out.println(books);
            getServletContext().setAttribute("books", books);

            req.setAttribute("books",books);

            req.getRequestDispatcher("/WEB-INF/Welcome.jsp")
                    .forward(req, resp);


            return;
        }

        if(!bookId.isBlank()){
            try {

                int id = Integer.parseInt(bookId);

                Book book = bookDAOImpl.findById(id);

                if (book == null) {
                    resp.sendError(HttpServletResponse.SC_NOT_FOUND,
                            "Book not found");
                    return;
                }

                req.setAttribute("book", book);
                req.getRequestDispatcher("/WEB-INF/Book.jsp")
                        .forward(req, resp);

                return;

            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }
        }


        resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Book not found");
    }


    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {

        // Security check
        if (!req.isUserInRole("LIBRARIAN")) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        if ("update".equals(req.getParameter("action"))) {

            int id = Integer.parseInt(req.getParameter("bookId"));

            Book book = new Book(
                    id,
                    req.getParameter("isbn"),
                    req.getParameter("title"),
                    req.getParameter("author"),
                    LocalDate.parse(req.getParameter("publishedYear")),
                    req.getParameter("genre"),
                    req.getParameter("synopsis")
            );

            bookDAOImpl.update(book);
            resp.sendRedirect(
                    req.getContextPath() +
                            "/book-controller?bookId=" + id
            );

            return;
        }






        try {

            String isbn = req.getParameter("isbn");
            String title = req.getParameter("title");
            String author = req.getParameter("author");
            String publishedYear = req.getParameter("published_year");
            String genre = req.getParameter("genre");
            String synopsis = req.getParameter("synopsis");

            Book book = new Book(
                    0,
                    isbn,
                    title,
                    author,
                    LocalDate.parse(publishedYear),
                    genre,
                    synopsis
            );

            int rowsAffected = bookDAOImpl.create(book);

            if (rowsAffected > 0) {

                resp.sendRedirect(
                        req.getContextPath() + "/book-controller"
                );

            } else {

                req.setAttribute("error", "Failed to add book.");

                req.getRequestDispatcher("/WEB-INF/Welcome.jsp")
                        .forward(req, resp);
            }

        } catch (Exception e) {

            req.setAttribute("error", "Could not add book. Check the ISBN and other details.");

            req.getRequestDispatcher("/WEB-INF/Welcome.jsp")
                    .forward(req, resp);
        }
    }


}
