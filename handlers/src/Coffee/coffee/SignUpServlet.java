package coffee;

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

        String fullName = req.getParameter("fullname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");
        String role = req.getParameter("role");

        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        if (!password.equals(confirmPassword)) {
            out.print("{\"status\":\"fail\",\"message\":\"Passwords do not match!\"}");
            return;
        }

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