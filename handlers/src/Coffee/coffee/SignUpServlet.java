package coffee;
/*
QR Attendy base on Website and WebApp lol
Develop by BELDAD-Ace on Github with the team group 1 for PR2
aka Jhon Benedict Belad

all rights reserved 2025

*/

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.*;
import java.sql.*;

@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
                //the part were java fuck with jabaskrep
        String fullName = req.getParameter("fullname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");
        String role = req.getParameter("role");
                //get respondsndnsds
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

                //if password and confirm password dont match
        if (!password.equals(confirmPassword)) {
            out.print("{\"status\":\"fail\",\"message\":\"Passwords do not match!\"}");
            return;
        }
            //another calling the souls of the programmer xD (no its creating our account bruh)
        try (Connection conn = DatabaseConnector.getConnection()) {
            String sql = "INSERT INTO users(fullname, email, password, role) VALUES(?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, fullName);
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.setString(4, role);
            stmt.executeUpdate();

            out.print("{\"status\":\"success\",\"message\":\"Account created successfully!\"}");
        } catch (Exception e) {
            e.printStackTrace();
            out.print("{\"status\":\"fail\",\"message\":\"Error: " + e.getMessage() + "\"}");
        }
    }
}