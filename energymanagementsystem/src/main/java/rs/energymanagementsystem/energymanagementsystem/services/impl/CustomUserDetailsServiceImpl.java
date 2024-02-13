package rs.energymanagementsystem.energymanagementsystem.services.impl;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import rs.energymanagementsystem.energymanagementsystem.entities.User;
import rs.energymanagementsystem.energymanagementsystem.exceptions.ResourceNotFoundException;
import rs.energymanagementsystem.energymanagementsystem.repositories.UserRepository;
import rs.energymanagementsystem.energymanagementsystem.services.CustomUserDetailsService;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService, UserDetailsManager, UserDetails {

    private UserRepository userRepository;

    public CustomUserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /** Used for application database update via html form **/
    @Override
    public User saveUserViaForm(User user) {
        return userRepository.save(user);
    }

    /** Create new user with default API save **/
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

    /** ---------------------------------------------------------------------------------------
    * Methods implemented from CustomUserDetailsManager
     --------------------------------------------------------------------------------------- **/
    @Override
    public void createUser(UserDetails user) {

    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return false;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    /** ---------------------------------------------------------------------------------------
     * Methods implemented from UserDetails
     --------------------------------------------------------------------------------------- **/
    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
