package org.example.libis.dao;



import org.example.libis.model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {


    @Override
    public List<Book> findAll(){


        System.out.println("Get all books called");

        List<Book> books = new ArrayList<>();


        String  returnAllBooks = "select * from book";

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
                            rs.getDate("published_year").toLocalDate(),
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

        String  returnSearchedBook = "select * from book where title = ?";
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
                            rs.getString("author"), rs.getDate("published_year").toLocalDate(),
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

        String  searchById = "select * from book where book_id = ?";
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
                            rs.getDate("published_year").toLocalDate(),
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

        String  searchById = "select * from book where isbn = ?";
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
                            rs.getDate("published_year").toLocalDate(),
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
    public int create(Book book) {

        int rowsAffected = -1;

        String sql = "insert into book( isbn, title, author, published_year, genre, synopsis) values (?,?,?,?,?,?)";
        //String updateSQL = " UPDATE members SET member_number = ? WHERE member_id = ? ";

        try(Connection connection = DataBaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setString(1,book.getIsbn());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setString(3, book.getAuthor());
            preparedStatement.setDate(4, Date.valueOf(book.getPublishedYear()));
            preparedStatement.setString(5, book.getGenre());
            preparedStatement.setString(6, book.getSynopsis());

            rowsAffected = preparedStatement.executeUpdate();



        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        return rowsAffected;



    }

    @Override
    public int update(Book book) {

        int rowsAffected = -1;

        String sql = "UPDATE book SET isbn = ?, title = ?, author = ?, published_year = ?, genre = ?, synopsis = ? WHERE book_id = ?";

        try(Connection connection = DataBaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setString(1, book.getIsbn());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setString(3, book.getAuthor());
            preparedStatement.setDate(4, Date.valueOf(book.getPublishedYear()));
            preparedStatement.setString(5, book.getGenre());
            preparedStatement.setString(6, book.getSynopsis());
            preparedStatement.setInt(7, book.getBookId());

            rowsAffected = preparedStatement.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return rowsAffected;
    }

    @Override
    public int delete(int id) {
        String sql = "DELETE FROM book  WHERE book_id = ?";

        int rowsAffected;


        try(Connection connection = DataBaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setInt(1,id);

            rowsAffected = preparedStatement.executeUpdate();

            if(rowsAffected>0){
                System.out.println("The is book is deleted");
            }



        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return rowsAffected;
    }


}
