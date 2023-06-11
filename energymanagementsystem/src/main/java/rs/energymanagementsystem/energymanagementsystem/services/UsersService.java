package rs.energymanagementsystem.energymanagementsystem.services;

import org.apache.catalina.User;
import rs.energymanagementsystem.energymanagementsystem.entities.Users;

import java.util.List;

public interface UsersService {
    Users saveUser(Users users);
    List<Users> getAllUsers();
    Users getUserById(Integer user_id);
    Users updateUsers(Users users, Integer users_id);
    void deleteUser(Integer user_id);
}