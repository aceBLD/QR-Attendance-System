package AttendyEngine.controller;

import AttendyEngine.datahandy.User;
import AttendyEngine.repohandy.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthDominator {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // JSON sign-in (AJAX)
    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody Map<String,String> body, HttpSession session) {
        String email = body.get("email");
        String password = body.get("password");

        if (email == null || password == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Email and password required"));
        }

        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);
            Authentication auth = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(auth);
            // attach spring security context to session for future requests
            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

            // fetch user object to return (optional)
            Optional<User> u = userRepository.findByEmail(email);
            u.ifPresent(user -> session.setAttribute("user", user));

            return ResponseEntity.ok(Map.of("success", true, "redirect", "/dashboard/index.html"));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(401).body(Map.of("error", "Invalid email or password"));
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(Map.of("error", "Authentication error"));
        }
    }

    // JSON signup (AJAX) — encodes password, saves user, then auto-authenticates
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody Map<String,String> body, HttpSession session) {
        String fullName = body.getOrDefault("fullName", body.getOrDefault("fullname", body.get("name")));
        String email = body.get("email");
        String password = body.get("password");
        String role = body.getOrDefault("role", "STUDENT");

        if (fullName == null || email == null || password == null) {
            return ResponseEntity.badRequest().body(Map.of("error","Missing required fields"));
        }

        if (userRepository.findByEmail(email).isPresent()) {
            return ResponseEntity.badRequest().body(Map.of("error","Email already exists"));
        }

        User newUser = new User();
        newUser.setFullName(fullName);
        newUser.setEmail(email);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setRole(role);
        userRepository.save(newUser);

        // auto authenticate
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);
            Authentication auth = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(auth);
            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
            session.setAttribute("user", newUser);
            return ResponseEntity.ok(Map.of("success", true, "redirect", "/dashboard/index.html"));
        } catch (Exception ex) {
            // User created but auto-login failed — still return success but instruct client to go to signin page
            return ResponseEntity.ok(Map.of("success", true, "redirect", "/started/index.html"));
        }
    }

    // helper - current user
    @GetMapping("/me")
    public ResponseEntity<?> me(HttpSession session) {
        Object u = session.getAttribute("user");
        if (u == null) return ResponseEntity.status(401).body(Map.of("error","not logged"));
        return ResponseEntity.ok(u);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> doLogout(HttpSession session) {
        session.invalidate();
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok(Map.of("success", true, "redirect", "/started/index.html"));
    }
}
