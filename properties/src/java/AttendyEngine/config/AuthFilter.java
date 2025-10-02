/*
CRITICAL ERROR OCCURED: This file wont let us go to dashboard even we log in so we had no choice but to temporarily disable it.



package AttendyEngine.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component; 

import java.io.IOException;

@Component
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String path = req.getRequestURI();
        HttpSession session = req.getSession(false);

        // Protect only /dashboard/**
        if (path.startsWith("/dashboard")) {
            if (session == null || session.getAttribute("user") == null) {
                // redirect only if user is not logged in
                res.sendRedirect("/started/index.html");
                return;
            }
        }

        // Allow everything else
        chain.doFilter(request, response);
    }
}
*/