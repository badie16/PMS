package com.badie.pms.db;

import com.badie.pms.model.User;

import java.sql.*;

public class UserDb {
    Connection con = MyConnection.connection();
    String sql;
    PreparedStatement ps;
    Statement st;
    public boolean exitUser(String email, String pass){
            sql="SELECT * FROM users WHERE user_email = '"+email+"'";
        try {
            st = con.createStatement();
            ResultSet rs= st.executeQuery(sql);
            if (rs.next()){
                int user_id = rs.getInt("user_id");
                System.out.println(user_id);
                User user = getUser(user_id);
                System.out.println(user);
                return pass.equals(user.user_pass) && email.equals(user.user_email);
            }
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return false;
    }
    Boolean exitUser(int user_id){
        sql = "SELECT * FROM users WHERE user_id = ?";
        try {
            ps=con.prepareStatement(sql);
            ps.setInt(1, user_id);
            return ps.executeQuery().next();
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return false;
    }
    public int getIdUserByEmail(String user_email){
        sql = "SELECT user_id FROM users WHERE user_email = ?";
        try {
            ps=con.prepareStatement(sql);
            ps.setString(1, user_email);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                 return rs.getInt("user_id");
        } catch (SQLException e) {
            System.out.println("error " + e);
        }
        return -1;
    }
    public User getUser(int user_id){
        if (!exitUser(user_id)) return null; //chick is user exited before start function getUser
        sql="SELECT * FROM users WHERE user_id = '"+user_id+"'";
        User user = new User();
        try {
            st =con.createStatement();
            ResultSet rs= st.executeQuery(sql);
            if (rs.next()){
                user.user_id = rs.getInt("user_id");
                user.user_email = rs.getString("user_email");
                user.user_pass = rs.getString("user_pass");
                user.user_type = rs.getString("user_type");
            }
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
//    public int setUser(User user) {
//        String sql = "INSERT INTO `users` " +
//                "(`firstName`, `mobileNumber`, `email`, `passWord`, `lastName`, `gender`) " +
//                "VALUES (?, ?, ?, ?, ?, ?)";
//        try {
//            ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
//            ps.setString(1, user.firstName);
//            ps.setString(2, user.mobileNumber);
//            ps.setString(3, user.email);
//            ps.setString(4, user.pass);
//            ps.setString(5, user.lastName);
//            ps.setString(6, user.gender.toString());
//            ps.executeUpdate();
//            ResultSet generatedKeys = ps.getGeneratedKeys();
//            if (generatedKeys.next()) {
//                user.id =  generatedKeys.getInt(1);
//            }
//        } catch (SQLException e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//        return user.id;
//    }
    public void updateUser(User user) {
        String sql = "UPDATE users " +
                "SET user_pass = ?, user_email = ? " +
                "WHERE user_id = ?";
        try {
            System.out.println(user.user_id);
            ps = con.prepareStatement(sql);
            ps.setString(1, user.user_pass);
            ps.setString(2, user.user_email);
            ps.setInt(3, user.user_id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
//    public void removeUser(int id){
//
//        sql = "DELETE FROM users WHERE id = '"
//                + id + "'";
//        try{
//            st = con.createStatement();
//            st.executeUpdate(sql);
//        }catch (SQLException e){
//            System.out.println("error " + e);
//        }
//    }
}
