package org.example.libis;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.libis.model.Book;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "book-view", value = "/book-controller", loadOnStartup = 1)
public class BookController extends HttpServlet {

    public void init(){
        List<Book> books = new ArrayList<>();


        Book book1 = new Book(
                1,
                "Things Fall Apart",
                "Chinua Achebe",
                "Historical Fiction",
                "A story about Okonkwo and the impact of colonialism on his Igbo community.",
                true
        );
        Book book2 = new Book(
                2,
                "The Great Gatsby",
                "F. Scott Fitzgerald",
                "Classic",
                "A mysterious millionaire pursues the dream of rekindling a lost love.",
                true
        );
        Book book3 = new Book(
                3,
                "1984",
                "George Orwell",
                "Dystopian",
                "A man struggles against a totalitarian government that controls every aspect of society.",
                false
        );
        Book book4 = new Book(
                4,
                "To Kill a Mockingbird",
                "Harper Lee",
                "Classic",
                "A young girl learns about racism and injustice through her father's defense of an accused man.",
                true
        );
        Book book5 = new Book(
                5,
                "The Hobbit",
                "J.R.R. Tolkien",
                "Fantasy",
                "Bilbo Baggins joins a group of dwarves on an adventure to reclaim their homeland.",
                false
        );
        Book book6 = new Book(
                6,
                "Animal Farm",
                "George Orwell",
                "Political Fiction",
                "Farm animals overthrow their human owner and attempt to create a society where all animals are equal.",
                true
        );

        Book book7 = new Book(
                7,
                "The Alchemist",
                "Paulo Coelho",
                "Adventure",
                "A young shepherd travels in search of a hidden treasure and discovers the importance of following his dreams.",
                true
        );

        Book book8 = new Book(
                8,
                "Pride and Prejudice",
                "Jane Austen",
                "Romance",
                "Elizabeth Bennet navigates love, family expectations, and her complicated relationship with Mr. Darcy.",
                false
        );

        Book book9 = new Book(
                9,
                "Harry Potter and the Philosopher's Stone",
                "J.K. Rowling",
                "Fantasy",
                "A young boy discovers that he is a wizard and begins his magical education at Hogwarts.",
                true
        );

        Book book10 = new Book(
                10,
                "The Catcher in the Rye",
                "J.D. Salinger",
                "Classic",
                "A teenager struggles with growing up and finding his place in a confusing adult world.",
                false
        );


        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);
        books.add(book6);
        books.add(book7);
        books.add(book8);
        books.add(book9);
        books.add(book10);
        getServletContext().setAttribute("books",books);


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String bookId = req.getParameter("bookId");

        if(bookId==null){

            req.getRequestDispatcher("/WEB-INF/Welcome.jsp")
                    .forward(req, resp);

            return;
        }

        int id = Integer.parseInt(bookId);

        List<Book> books = (List<Book>) getServletContext().getAttribute("books");

        for(Book book:books){
            if(book.getBookId()==id){
                req.setAttribute("book",book);
                req.getRequestDispatcher("/WEB-INF/Book.jsp").forward(req,resp);
                return;
            }
        }


        resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Book not found");
    }


}
