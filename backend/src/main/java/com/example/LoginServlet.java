// LoginServlet.java (Semplificato)
package com.example;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;

public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String user = request.getParameter("username");
        String pass = request.getParameter("password");

        try (Connection conn = Database.getConnection()) {
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, user);
            stmt.setString(2, pass);
            
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()) {
                response.getWriter().print("Login riuscito!");
            } else {
                response.getWriter().print("Credenziali errate!");
            }
            
        } catch (SQLException e) {
            response.getWriter().print("Errore: " + e.getMessage());
        }
    }
}