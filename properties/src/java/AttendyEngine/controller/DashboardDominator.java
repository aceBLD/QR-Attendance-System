package AttendyEngine.controller;

import AttendyEngine.datahandy.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashboardDominator {

    @GetMapping("/dashboard/me")
    public ResponseEntity<?> me(HttpSession session) {
        User u = (User) session.getAttribute("user");
        if (u == null) return ResponseEntity.status(401).body("Not logged in");
        return ResponseEntity.ok(u);
    }
}
