package org.example.libis.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoanDAOImpl implements LoanDAO {

    @Override
    public int create(int memberId, int bookId) {

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
    public int countOpenByMember(int memberId) {
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
    public int hasOpenLoan(int bookId) {
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
}
