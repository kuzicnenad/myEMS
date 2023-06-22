package rs.energymanagementsystem.energymanagementsystem.services;

import rs.energymanagementsystem.energymanagementsystem.entities.UsersEntity;

import java.util.List;

public interface UsersService {
    UsersEntity saveUser(UsersEntity usersEntity);
    List<UsersEntity> getAllUsers();
    UsersEntity getUserById(Integer user_id);
    UsersEntity updateUsers(UsersEntity usersEntity, Integer users_id);
    void deleteUser(Integer user_id);
    List<UsersEntity> getLastUsers();
}