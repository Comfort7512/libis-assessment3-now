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

                System.out.println("This is the book found"+book);

                req.setAttribute("book",book);
                req.getRequestDispatcher("/WEB-INF/Book.jsp").forward(req,resp);


            } catch (NumberFormatException e) {
                throw new RuntimeException(e);
            }
        }








//        List<Book> books = (List<Book>) getServletContext().getAttribute("books");
//
//        for(Book book:books){
//            if(book.getBookId()==id){
//                req.setAttribute("book",book);
//                req.getRequestDispatcher("/WEB-INF/Book.jsp").forward(req,resp);
//                return;
//            }
//        }


        resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Book not found");
    }


}
