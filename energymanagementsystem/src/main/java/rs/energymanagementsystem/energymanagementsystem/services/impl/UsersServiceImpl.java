package rs.energymanagementsystem.energymanagementsystem.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.energymanagementsystem.energymanagementsystem.entities.User;
import rs.energymanagementsystem.energymanagementsystem.exceptions.ResourceNotFoundException;
import rs.energymanagementsystem.energymanagementsystem.repositories.UserRepository;
import rs.energymanagementsystem.energymanagementsystem.services.UsersService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

    private final UserRepository userRepository;
    private User user;

    /** Used for application database update via html form **/
    @Override
    public User saveUserViaForm(User user) {
        return userRepository.save(user);
    }

    /** Create new user with default API save **/
    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getUsersList();
    }

     public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User", "id", id));
    }

    /** Update user details with custom API save and native query **/
     @Override
    public User updateUser(User usersEntity, Long id) {
        // check if userId exists in database
        User existingUser = userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User", "id", id));
        existingUser.setName(usersEntity.getName());
        existingUser.setUsername(usersEntity.getUsername());
        existingUser.setEmail(usersEntity.getEmail());
        existingUser.setPassword(usersEntity.getPassword());
        existingUser.setLastUpdate(usersEntity.getLastUpdate());
        // save existing alarmData to DB
        userRepository.save(existingUser);
        return existingUser;
    }

    @Override
    public void deleteUser(Long id) {
        // check if user exists in database
        userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User", "id", id));
        userRepository.deleteById(id);
    }

    /** ---------------------------------------------------------------------------------------
     * Change device active flag, 0 -> Inactive, 1 -> Active
     * Get active devices list
     --------------------------------------------------------------------------------------- **/
    @Override
    public void userActiveFlag(Long id) {
        // check if device exists in database
        userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User", "id", id));
        userRepository.userActiveFlag(id);
    }

    @Override
    public boolean existsByUsername(String user) {
        return userRepository.existsByUsername(user);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }


}
