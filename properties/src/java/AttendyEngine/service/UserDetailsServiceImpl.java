package AttendyEngine.service;

import AttendyEngine.datahandy.User;
import AttendyEngine.repohandy.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = repo.findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(
            u.getEmail(),
            u.getPassword(),
            List.of(new SimpleGrantedAuthority("ROLE_" + (u.getRole() == null ? "STUDENT" : u.getRole())))
        );
    }
}
