package rs.energymanagementsystem.energymanagementsystem.services.impl;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rs.energymanagementsystem.energymanagementsystem.entities.User;
import rs.energymanagementsystem.energymanagementsystem.exception.ResourceNotFoundException;
import rs.energymanagementsystem.energymanagementsystem.repositories.UserRepository;
import rs.energymanagementsystem.energymanagementsystem.services.CustomUserDetailsService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService, UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /** Create new user with default API save**/
    @Override
    public User saveUser(User user) {
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
        // check if user_id exists in database
        User existingUser = userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User", "id", id));
        existingUser.setName(usersEntity.getName());
        existingUser.setUsername(usersEntity.getUsername());
        existingUser.setEmail(usersEntity.getEmail());
        existingUser.setPassword(usersEntity.getPassword());
        existingUser.setLast_update(usersEntity.getLast_update());
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

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with username or email: "+ usernameOrEmail));

        Set<GrantedAuthority> authorities = user
                .getRoles()
                .stream()
                .map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                authorities);
    }
}
