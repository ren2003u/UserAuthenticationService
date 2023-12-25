package service;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Method to register a new user
    public User registerUser(User user) {
        // Check if user already exists
        if (userRepository.existsByUsername(user.getUsername()) || userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalStateException("Username or email already taken");
        }
        // Here you can also add password encryption logic before saving

        return userRepository.save(user);
    }

    // Method to find a user by username
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Method to check if a username exists
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    // Method to check if an email exists
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    // Additional methods as required...
}
