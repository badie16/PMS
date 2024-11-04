package com.badie.pms.db;

import javafx.scene.control.Alert;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class MyConnection {
    final static String DB_URL="jdbc:mysql://localhost/pmsDb";
    final static String DbUSER="root";
    final static  String DbPASS="";
    static Connection connect;
    public static Connection connection()
    {
        try {
            connect = DriverManager.getConnection(DB_URL, DbUSER, DbPASS);
            return connect;
        }catch(SQLException e) {
            System.out.println("error in file MyConnection");
            return null;
        }
    }


}

