package org.example.libis.dao;



import org.example.libis.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {


    @Override
    public List<Book> findAll(){


        System.out.println("Get all books called");

        List<Book> books = new ArrayList<>();


        String  returnAllBooks = "select * from books";

        try(Connection connection = DataBaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(returnAllBooks);
            ResultSet rs = preparedStatement.executeQuery()
        ){

                while (rs.next()){
                    Book  book = new Book(
                            rs.getInt("book_Id"),
                            rs.getString("isbn"),
                            rs.getString("title"),
                            rs.getString("author"),
                            rs.getString("published_year"),
                            rs.getString("genre"),
                            rs.getString("synopsis")
//                            rs.getBoolean("available")
                    );
                    books.add(book);
                }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return books;
    }

    @Override
    public Book searchByTitle(String title){

        String  returnSearchedBook = "select * from books where title = ?";
        Book book = null;
        try(Connection connection = DataBaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(returnSearchedBook);
        ){
            preparedStatement.setString(1, title);

            try (ResultSet rs = preparedStatement.executeQuery()) {

                if (rs.next()) {

                      book = new Book(
                            rs.getInt("book_Id"),
                            rs.getString("isbn"),
                            rs.getString("title"),
                            rs.getString("author"),
                            rs.getString("published_year"),
                            rs.getString("genre"),
                            rs.getString("synopsis")
//                            rs.getBoolean("available")
                    );
                }
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return book;
    }

    @Override
    public Book findById(int id){

        String  searchById = "select * from books where book_id = ?";
        Book book = null;
        try(Connection connection = DataBaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(searchById);
        ){
            preparedStatement.setInt(1, id);

            try (ResultSet rs = preparedStatement.executeQuery()) {

                if (rs.next()) {


                    book = new Book(
                            rs.getInt("book_Id"),
                            rs.getString("isbn"),
                            rs.getString("title"),
                            rs.getString("author"),
                            rs.getString("published_year"),
                            rs.getString("genre"),
                            rs.getString("synopsis")
//                            rs.getBoolean("available")
                    );
                }
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return book;
    }

    @Override
    public Book findByIsbn(String isbn){

        String  searchById = "select * from books where isbn = ?";
        Book book = null;
        try(Connection connection = DataBaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(searchById);
        ){
            preparedStatement.setString(1, isbn);

            try (ResultSet rs = preparedStatement.executeQuery()) {

                if (rs.next()) {


                    book = new Book(
                            rs.getInt("book_Id"),
                            rs.getString("isbn"),
                            rs.getString("title"),
                            rs.getString("author"),
                            rs.getString("published_year"),
                            rs.getString("genre"),
                            rs.getString("synopsis")
//                            rs.getBoolean("available")
                    );
                }
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return book;
    }

    @Override
    public void create(Book book) {

    }

    @Override
    public void update(Book book) {

    }

    @Override
    public void delete(int id) {
        
    }
}
