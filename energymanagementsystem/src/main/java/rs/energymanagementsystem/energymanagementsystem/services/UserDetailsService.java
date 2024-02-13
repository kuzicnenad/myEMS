package rs.energymanagementsystem.energymanagementsystem.services;

import org.springframework.security.core.userdetails.UserDetails;
import rs.energymanagementsystem.energymanagementsystem.entities.User;

import java.util.List;

public interface UserDetailsService {

    User saveUserViaForm(User user);
    User createUser(User user);

    List<User> getAllUsers();

    User getUserById(Long user_id);

    User updateUser(User user, Long users_id);

    void deleteUser(Long user_id);

    /** Necessary Information to login **/
    UserDetails loadUserByUsername(String usernameOrEmail);

    /** ---------------------------------------------------------------------------------------
     * Change device active flag, 0 -> Inactive, 1 -> Active
     * Get active devices list
     --------------------------------------------------------------------------------------- **/
    void userActiveFlag(Long id);

}