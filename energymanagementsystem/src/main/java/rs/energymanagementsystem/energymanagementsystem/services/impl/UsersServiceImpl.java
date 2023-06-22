package rs.energymanagementsystem.energymanagementsystem.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import rs.energymanagementsystem.energymanagementsystem.entities.UsersEntity;
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

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public UsersEntity saveUser(UsersEntity usersEntity) {
        return usersRepository.save(usersEntity);
    }

    @Override
    public List<UsersEntity> getAllUsers() {
        return usersRepository.findAll();
    }

    public UsersEntity getUserById(Integer user_id){
        return usersRepository.findById(user_id).orElseThrow(() ->
                new ResourceNotFoundException("User", "user_id", user_id));
    }



    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public UsersEntity updateUsers(UsersEntity usersEntity, Integer user_id) {
        // check if user_id exists in database
        UsersEntity existingUser = usersRepository.findById(user_id).orElseThrow(() ->
                new ResourceNotFoundException("User", "user_id", user_id));
        existingUser.setUser_login(usersEntity.getUser_login());
        existingUser.setFirst_name(usersEntity.getFirst_name());
        existingUser.setLast_name(usersEntity.getLast_name());
        existingUser.setJob_title(usersEntity.getJob_title());
        existingUser.setTime_stamp(usersEntity.getTime_stamp());
        // save existing alarmData to DB
        usersRepository.save(existingUser);
        return existingUser;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public void deleteUser(Integer user_id) {
        // check if user exists in database
        usersRepository.findById(user_id).orElseThrow(() ->
                new ResourceNotFoundException("User", "user_id", user_id));
        usersRepository.deleteById(user_id);
    }

    @Override
    public List<UsersEntity> getLastUsers() {
        return usersRepository.getLastUsers();
    }
}
