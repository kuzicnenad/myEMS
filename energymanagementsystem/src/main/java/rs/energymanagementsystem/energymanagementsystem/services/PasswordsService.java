package rs.energymanagementsystem.energymanagementsystem.services;

import rs.energymanagementsystem.energymanagementsystem.entities.Passwords;

import java.util.List;

public interface PasswordsService {

    // not fully implemented yet
    // Passwords savePassword(Passwords passwords);
    List<Passwords> getAllPasswords();
    Passwords getPasswordById(Integer hash_algorithm_id);
    //Passwords updatePassword(Passwords passwords, Integer hash_algorithm_id);

}
