package rs.energymanagementsystem.energymanagementsystem.services.impl;

import org.springframework.stereotype.Service;
import rs.energymanagementsystem.energymanagementsystem.entities.Passwords;
import rs.energymanagementsystem.energymanagementsystem.exception.ResourceNotFoundException;
import rs.energymanagementsystem.energymanagementsystem.repositories.PasswordsRepository;
import rs.energymanagementsystem.energymanagementsystem.services.PasswordsService;

import java.util.List;

@Service
public class PasswordsServiceImpl implements PasswordsService {

    private PasswordsRepository passwordsRepository;

    public PasswordsServiceImpl(PasswordsRepository passwordsRepository){
        this.passwordsRepository = passwordsRepository;
    }

    @Override
    public Passwords savePassword(Passwords passwords) {
        return passwordsRepository.save(passwords);
    }

    @Override
    public List<Passwords> getAllPasswords() {
        return passwordsRepository.findAll();
    }

    @Override
    public Passwords getPasswordById(Integer hash_algorithm_id) {
        return passwordsRepository.findById(hash_algorithm_id).orElseThrow(() ->
                new ResourceNotFoundException("Password", "hash_algorithm_id", hash_algorithm_id));
    }
// updatePassword is Unavailable in first version, to be implemented in next version with java spring security

}

