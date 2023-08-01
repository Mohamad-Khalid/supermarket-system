package com.gomarket.supermarket.models;
import java.sql.*;
public class DBConnection {
    private static Connection con;
    private final static String user = "root";
    private final static String password = "12345";
    private final static String dbPath = "jdbc:mysql://localhost/Supermarket";
    private DBConnection(){
        con = null;
    }
    public static Connection openDBConnection() throws SQLException {
        if (con == null){
            con = DriverManager.getConnection(dbPath,user,password);
            System.out.println("connected to db");
        }
        return con;
    }
    public static void closeDBConnection() throws SQLException {
        if (con != null){
            con = null;
        }
    }
}
