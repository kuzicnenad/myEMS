package rs.energymanagementsystem.energymanagementsystem.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.energymanagementsystem.energymanagementsystem.entities.Passwords;
import rs.energymanagementsystem.energymanagementsystem.repositories.PasswordsRepository;
import rs.energymanagementsystem.energymanagementsystem.services.PasswordsService;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PasswordServiceImpl implements PasswordsService {

    private final PasswordsRepository passwordsRepository;
    private Passwords passwords;

    @Override
    public Passwords createPassword(Passwords passwords, String connectedUser) {
        passwords.setUserName(connectedUser);
        return passwordsRepository.save(passwords);
    }

    @Override
    public List<Passwords> getAllPasswords() {
        return null;
    }
}
