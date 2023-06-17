package rs.energymanagementsystem.energymanagementsystem.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.energymanagementsystem.energymanagementsystem.entities.Users;
import rs.energymanagementsystem.energymanagementsystem.exception.ResourceNotFoundException;
import rs.energymanagementsystem.energymanagementsystem.repositories.UsersRepository;
import rs.energymanagementsystem.energymanagementsystem.services.UsersService;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    @Override
    public Users saveUser(Users users) {
        return usersRepository.save(users);
    }

    @Override
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    public Users getUserById(Integer user_id){
        return usersRepository.findById(user_id).orElseThrow(() ->
                new ResourceNotFoundException("User", "user_id", user_id));
    }

    @Override
    public Users updateUsers(Users users, Integer user_id) {
        // check if user_id exists in database
        Users existingUser = usersRepository.findById(user_id).orElseThrow(() ->
                new ResourceNotFoundException("User", "user_id", user_id));
        existingUser.setUser_login(users.getUser_login());
        existingUser.setFirst_name(users.getFirst_name());
        existingUser.setLast_name(users.getLast_name());
        existingUser.setJob_title(users.getJob_title());
        existingUser.setTime_stamp(users.getTime_stamp());
        // save existing alarmData to DB
        usersRepository.save(existingUser);
        return existingUser;
    }

    @Override
    public void deleteUser(Integer user_id) {
        // check if user exists in database
        usersRepository.findById(user_id).orElseThrow(() ->
                new ResourceNotFoundException("User", "user_id", user_id));
        usersRepository.deleteById(user_id);
    }
}
