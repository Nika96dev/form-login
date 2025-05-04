// Database.java (Semplificato)
package com.example;

import java.sql.*;

public class Database {
    public static Connection getConnection() throws SQLException {
        String URL = "jdbc:mysql://localhost:3306/user_auth";
        String USER = "root";
        String PASS = "";
        return DriverManager.getConnection(URL, USER, PASS);
    }
}