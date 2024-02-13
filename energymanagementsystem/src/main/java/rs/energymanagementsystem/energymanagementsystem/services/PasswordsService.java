package rs.energymanagementsystem.energymanagementsystem.services;

import rs.energymanagementsystem.energymanagementsystem.entities.Passwords;

import java.security.Principal;
import java.util.List;

public interface PasswordsService {

    /** Save password changes for LOG tracing */
    Passwords createPassword(Passwords passwords, String connectedUser);

    /** For future LOG implementation to show when password was changed */
    List<Passwords> getAllPasswords();
}
