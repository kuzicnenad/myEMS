package rs.energymanagementsystem.energymanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.energymanagementsystem.energymanagementsystem.entities.Passwords;
import rs.energymanagementsystem.energymanagementsystem.repositories.PasswordsRepository;
import rs.energymanagementsystem.energymanagementsystem.services.PasswordsService;

import java.util.List;

@RequestMapping
@RestController("/api/passwords")
public class PasswordsController {

    private PasswordsService passwordsService;

    public PasswordsController(PasswordsService passwordsService){
        this.passwordsService = passwordsService;
    }

    // POST passwords REST API
    @PostMapping
    public ResponseEntity<Passwords> savePasswordsData(@RequestBody Passwords passwords){
        return new ResponseEntity<Passwords>(passwordsService.savePassword(passwords), HttpStatus.CREATED);
    }

    // GET all users REST API
    @GetMapping
    public List<Passwords> getAllPasswords() {
        return passwordsService.getAllPasswords();
    }

    // GET by ID user REST API
    // http://localhost:8080/api/passwords/hash_algorithm_id(number)
    @GetMapping("{hash_algorithm_id}")
    public ResponseEntity<Passwords> getPasswordById(@PathVariable ("hash_algorithm_id") Integer hash_algorithm_id){
        return new ResponseEntity<Passwords>(passwordsService.getPasswordById(hash_algorithm_id), HttpStatus.OK);
    }

    // UPDATE not implemented yet
}
