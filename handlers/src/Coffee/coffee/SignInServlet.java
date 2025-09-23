package coffee;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.*;
import java.sql.*;

@WebServlet("/signin")
public class SignInServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        try (Connection conn = DatabaseConnector.getConnection()) {
            String sql = "SELECT fullname, role FROM users WHERE email=? AND password=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String fullname = rs.getString("fullname");
                String role = rs.getString("role");

                HttpSession session = req.getSession();
                session.setAttribute("email", email);
                session.setAttribute("fullname", fullname);
                session.setAttribute("role", role);

                resp.setContentType("application/json");
                resp.getWriter().print("{\"status\":\"success\",\"message\":\"Welcome "
                        + fullname + "!\",\"role\":\"" + role + "\"}");
            } else {
                resp.setContentType("application/json");
                resp.getWriter().print("{\"status\":\"fail\",\"message\":\"Invalid credentials\"}");
            }

        } catch (Exception e) {
            e.printStackTrace();
            resp.setContentType("application/json");
            resp.getWriter().print("{\"status\":\"fail\",\"message\":\"Error: " + e.getMessage() + "\"}");
        }
    }
}