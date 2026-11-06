package org.example.libis.data;

import org.example.libis.model.User;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    User user = null;
    public User validate(String email, String pass) {
        System.out.println("Validate called");

        String loginValidateQuery = "select * from users where email=? and password=?";

        try(Connection connection = DataBaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(loginValidateQuery)
            ) {
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,pass);


            try(ResultSet rs = preparedStatement.executeQuery()){
                while(rs.next()){
                    user = new User(
                            rs.getInt("user_Id"),
                            rs.getString("name"),
                            rs.getString("surname"),
                            rs.getString("email"),
                            rs.getString("password")
                    );

                }
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;



    }

    public int registerUser(String email,
                             String password,
                             String firstName,
                             String lastName){
        int rowsAffected;

        String createUserSQL = "insert into users(name,surname,email,password) values(?,?,?,?)";

        try(Connection connection = DataBaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(createUserSQL))
            {
                preparedStatement.setString(1,firstName);
                preparedStatement.setString(2,lastName);
                preparedStatement.setString(3,email);
                preparedStatement.setString(4,password);

             rowsAffected = preparedStatement.executeUpdate();

             if(rowsAffected>0){
                 System.out.println("The is created");
             }



            } catch (Exception e) {
            throw new RuntimeException(e);
        }

    return rowsAffected;

    }
}
