package org.example.libis.dao;

import org.example.libis.model.Book;
import org.example.libis.model.Role;
import org.example.libis.model.Member;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDAOImpl implements MemberDAO{

    // heleper member to reduce redudancy
    private Member memberMap(ResultSet resultSet) throws SQLException {


        String roleStr = resultSet.getString("role");

        Role role = roleStr != null ? Role.valueOf(roleStr.toUpperCase()) : null;

        return new Member(
                    resultSet.getInt("member_id"),
                    resultSet.getString("member_number"),
                    resultSet.getString("name"),
                    resultSet.getString("surname"),
                    resultSet.getString("email"),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    role
            );



    }

    public Member validate(String email, String pass) {
        System.out.println("Validate called");

        Member member = null;


        String loginValidateQuery = "select * from members where email=? and password=?";

        try(Connection connection = DataBaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(loginValidateQuery)
            ) {
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,pass);


            try(ResultSet rs = preparedStatement.executeQuery()){
                if(rs.next()){

                    member = memberMap(rs);

                }
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return member;



    }

    public int registerUser(String email,
                             String password,
                             String firstName,
                             String lastName){
        int rowsAffected;

        String createUserSQL = "insert into members(name,surname,email,password) values(?,?,?,?)";

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

    @Override
    public List<Member> findAll() {

        List<Member> members = new ArrayList<>();


        String  returnAllMembers = "select * from members";

        try(Connection connection = DataBaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(returnAllMembers);
            ResultSet rs = preparedStatement.executeQuery()
        ){


            while (rs.next()){

                String roleStr = rs.getString("role");
                Role role = (roleStr!=null) ? Role.valueOf(roleStr.toUpperCase()) : null;


                Member  member1 = new Member(
                        rs.getInt("member_id"),
                        rs.getString("member_number"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("email"),
                        rs.getString("username"),
                        rs.getString("password"),
                        role
                );
                members.add(member1);
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return members;
    }

    @Override
    public Member findById(int id) {
        Member member = null;

        String  returnMember = "select * from members where member_id = ?";

        try(Connection connection = DataBaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(returnMember);

        ){
            preparedStatement.setInt(1,id);


            try(ResultSet rs = preparedStatement.executeQuery()) {

                if (rs.next()) {

                    String roleStr = rs.getString("role");
                    Role role = (roleStr != null) ? Role.valueOf(roleStr.toUpperCase()) : null;


                    member = new Member(
                            rs.getInt("member_id"),
                            rs.getString("member_number"),
                            rs.getString("name"),
                            rs.getString("surname"),
                            rs.getString("email"),
                            rs.getString("username"),
                            rs.getString("password"),
                            role
                    );

                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return member;
    }

    @Override
    public Member searchByUsername(String keyword) {

        Member member = null;

        String  returnMember = "select * from members where username = ?";

        try(Connection connection = DataBaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(returnMember);

        ){
            preparedStatement.setString(1,keyword);


            try(ResultSet rs = preparedStatement.executeQuery()) {

                if (rs.next()) {

                    String roleStr = rs.getString("role");
                    Role role = (roleStr != null) ? Role.valueOf(roleStr.toUpperCase()) : null;


                    member = new Member(
                            rs.getInt("member_id"),
                            rs.getString("member_number"),
                            rs.getString("name"),
                            rs.getString("surname"),
                            rs.getString("email"),
                            rs.getString("username"),
                            rs.getString("password"),
                            role
                    );

                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return member;
    }

    @Override
    public Member searchByMemberNumber(String keyword) {
        Member member = null;

        String  sql = "select * from members where member_number = ?";

        try(Connection connection = DataBaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ){
            preparedStatement.setString(1,keyword);


            try(ResultSet rs = preparedStatement.executeQuery()) {

                if (rs.next()) {
                    member = memberMap(rs);


                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return member;
    }

    @Override
    public Member searchByName(String keyword) {
        return null;
    }

    @Override
    public Member searchByEmail(String keyword) {
        Member member = null;

        String  sql = "select * from members where email = ?";

        try(Connection connection = DataBaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ){
            preparedStatement.setString(1,keyword);


            try(ResultSet rs = preparedStatement.executeQuery()) {

                if (rs.next()) {
                    member = memberMap(rs);


                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return member;
    }

    @Override
    public void create(Member member) {

    }

    @Override
    public void update(Member member) {

    }

    @Override
    public void delete(int id) {

    }
}
