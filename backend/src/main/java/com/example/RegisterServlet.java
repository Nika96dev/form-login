// RegisterServlet.java (Semplificato)
package com.example;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;

public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String user = request.getParameter("username");
        String pass = request.getParameter("password");

        try (Connection conn = Database.getConnection()) {
            String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, user);
            stmt.setString(2, pass); // ❗ Password in chiaro (NON SICURO)
            
            stmt.executeUpdate();
            response.getWriter().print("Registrato!");
            
        } catch (SQLException e) {
            response.getWriter().print("Errore: " + e.getMessage());
        }
    }
}