package coffee;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebFilter({"/Main.html", "/Main_Mobil.html"})
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

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

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void destroy() {}
}