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

@WebServlet("/signin")
public class SignInServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String email = req.getParameter("email");//YOU know this aint u
        String password = req.getParameter("password"); //ur ass password

                //we calling sql that WHERE IS OUR INFORMATION
        try (Connection conn = DatabaseConnector.getConnection()) {
            String sql = "SELECT fullname, role FROM users WHERE email=? AND password=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();


            //ignore this its just getting ur info MUWEUWHEHWEHWEHWEHEH joke idk what is this tho
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