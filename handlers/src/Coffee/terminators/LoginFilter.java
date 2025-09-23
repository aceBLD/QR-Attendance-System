package terminators;
/*
QR Attendy base on Website and WebApp lol
Develop by BELDAD-Ace on Github with the team group 1 for PR2
aka Jhon Benedict Belad

all rights reserved 2025

*/

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;

import java.io.IOException;
                //prevent u from going to sign in page obviously HHAHASHAHAHAHDAHHAAH
@WebFilter({"/Main.html", "/Main_Mobil.html"})
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
                //reqiesting kasi oo
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
                
        //if alreadi login then SHOO
        if (session != null && session.getAttribute("email") != null) {
            // Already logged in → go to role dashboard
            
            String role = (String) session.getAttribute("role");
            if ("Teacher".equals(role)) {
                resp.sendRedirect(req.getContextPath() + "/dashboard/TeacherDash.html");
            } else if ("Student".equals(role)) {
                resp.sendRedirect(req.getContextPath() + "/dashboard/StudentDash.html");
            } else if ("Admin".equals(role)) {
                resp.sendRedirect(req.getContextPath() + "/dashboard/CoreDash.html");
            } else {
                resp.sendRedirect(req.getContextPath() + "/dashboard/Main.html");
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    //Alyanna Membrano <3
    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void destroy() {}
}