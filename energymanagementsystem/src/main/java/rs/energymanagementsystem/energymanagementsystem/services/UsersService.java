package rs.energymanagementsystem.energymanagementsystem.services;

import rs.energymanagementsystem.energymanagementsystem.entities.User;

import java.util.List;

public interface UsersService {

    User saveUserViaForm(User user);
    User createUser(User user);

    List<User> getAllUsers();

    User getUserById(Long userId);

    User updateUser(User user, Long usersId);

    void deleteUser(Long userId);

    /** Necessary Information to login **/
    /*UserDetails loadUserByUsername(String usernameOrEmail);*/

    /** ---------------------------------------------------------------------------------------
     * Change device active flag, 0 -> Inactive, 1 -> Active
     * Get active devices list
     --------------------------------------------------------------------------------------- **/
    void userActiveFlag(Long id);

    boolean existsByUsername(String user);

    boolean existsByEmail(String email);

}