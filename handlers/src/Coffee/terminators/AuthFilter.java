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
            //anti roberring in this html pages 
@WebFilter({"dashboard/TeacherDash.html", "dashboard/StudentDash.html", "dashboard/CoreDash.html"})
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        // Prevent cached dashboard after logout and no witnesses of u being absent
        resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        resp.setHeader("Pragma", "no-cache");
        resp.setDateHeader("Expires", 0);

        //if u are signed in or nah nah
        if (session != null && session.getAttribute("email") != null) {
            chain.doFilter(request, response);
        } else {//if not then get outajdsasdjaskdjaskd
            resp.sendRedirect(req.getContextPath() + "/Main.html");
        }
    }

    //ignore this shit
    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void destroy() {}
}
