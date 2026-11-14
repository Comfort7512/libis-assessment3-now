package org.example.libis.dao;

import org.example.libis.model.Loan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LoanDAOImpl implements LoanDAO {

    @Override
    public int create(int memberId, int bookId) { // borrow book

        String sql = "INSERT INTO loans(member_id, book_id, borrowed_on) VALUES (?, ?, CURDATE()) ";
        int rowsAffected = -1;


        try(Connection connection = DataBaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setInt(1,memberId);
            preparedStatement.setInt(2, bookId);

            rowsAffected = preparedStatement.executeUpdate();



        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        return rowsAffected;


    }

    @Override
    public int countOpenByMember(int memberId) { // count number of books not returned by user
        String sql = "select count(*) from loans where member_id = ? and returned_on is null";

        int rowsAffected = -1;


        try(Connection connection = DataBaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setInt(1,memberId);
            //preparedStatement.setInt(2, bookId);

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                   return resultSet.getInt(1);
                }
            }

            //rowsAffected = preparedStatement.executeUpdate();



        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        return 0;

    }

    @Override
    public int hasOpenLoan(int bookId) { //checks whether book is returned
        String sql = "select count(*) from loans where book_id = ? and returned_on is null";

       // int rowsAffected = -1;


        try(Connection connection = DataBaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setInt(1,bookId);
            //preparedStatement.setInt(2, bookId);

            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if(resultSet.next()){
                    return resultSet.getInt(1);
                }
            }

            //rowsAffected = preparedStatement.executeUpdate();



        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        return 0;

    }

    @Override
    public int markRetuned(int bookId) { //checks whether book is returned

        String sql = "UPDATE loans SET returned_on = CURDATE() WHERE book_id = ? AND returned_on IS NULL";

        int rowsAffected = 0;


        try(Connection connection = DataBaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setInt(1,bookId);


//            try(ResultSet resultSet = preparedStatement.executeQuery()){
//                if(resultSet.next()){
//                    return resultSet.getInt(1);
//                }
//            }
            rowsAffected = preparedStatement.executeUpdate();

            //rowsAffected = preparedStatement.executeUpdate();



        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        return rowsAffected;

    }

    @Override
    public List<Loan> findOpenByMember(int memberId) { // returns all books the member currently has out:

        String sql = "select * from loans where member_id = ? and returned_on is null";
        List<Loan> loans = new ArrayList<>();

        try(Connection connection = DataBaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setInt(1,memberId);


            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    //LocalDate date = resultSet.getDate("returned_on").toLocalDate() !=null ? d;
                    Loan loan = new Loan(
                            resultSet.getInt("loan_id"),
                            resultSet.getInt("member_id"),
                            resultSet.getInt("book_id"),
                            resultSet.getDate("borrowed_on").toLocalDate(),
                  null
                    );

                    loans.add(loan);
                }
            }

            //rowsAffected = preparedStatement.executeUpdate();



        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        return loans;

    }

    @Override
    public List<Loan> findHistoryByMember(int memberId) { // returns all books the member currently has out:

        String sql = "select * from loans where member_id = ?";

        List<Loan> loans = new ArrayList<>();

        try(Connection connection = DataBaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setInt(1,memberId);


            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    //LocalDate date = resultSet.getDate("returned_on").toLocalDate() !=null ? d;
                    LocalDate returnedOn = null;

                    if(resultSet.getDate("returned_on") != null){
                        returnedOn =
                                resultSet.getDate("returned_on").toLocalDate();
                    }

                    Loan loan = new Loan(
                            resultSet.getInt("loan_id"),
                            resultSet.getInt("member_id"),
                            resultSet.getInt("book_id"),
                            resultSet.getDate("borrowed_on").toLocalDate(),
                            returnedOn

                    );

                    loans.add(loan);
                }
            }

            //rowsAffected = preparedStatement.executeUpdate();



        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        return loans;

    }


}
