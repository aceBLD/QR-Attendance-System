package AttendyEngine.service;

import AttendyEngine.datahandy.User;
import AttendyEngine.repohandy.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User save(User u) {
        return userRepository.save(u);
    }
}
