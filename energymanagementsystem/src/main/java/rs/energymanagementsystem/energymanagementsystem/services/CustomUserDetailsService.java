package rs.energymanagementsystem.energymanagementsystem.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import rs.energymanagementsystem.energymanagementsystem.entities.User;

import java.util.List;

public interface CustomUserDetailsService {
    User saveUser(User user);

    List<User> getAllUsers();

    User getUserById(Long user_id);

    User updateUser(User user, Long users_id);

    void deleteUser(Long user_id);

    /** Necessary Information to login **/
    UserDetails loadUserByUsername(String usernameOrEmail);

}