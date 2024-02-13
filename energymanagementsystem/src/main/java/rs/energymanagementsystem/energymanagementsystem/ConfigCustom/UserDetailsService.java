package rs.energymanagementsystem.energymanagementsystem.ConfigCustom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rs.energymanagementsystem.energymanagementsystem.entities.User;
import rs.energymanagementsystem.energymanagementsystem.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findByUsername(username);

        if(user!=null) return new CustomUserDetails(user);

        throw new UsernameNotFoundException("User " + username + "not found.");
    }
}
