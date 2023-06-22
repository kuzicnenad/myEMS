package rs.energymanagementsystem.energymanagementsystem.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.energymanagementsystem.energymanagementsystem.entities.Passwords;
import rs.energymanagementsystem.energymanagementsystem.services.PasswordsService;

import java.util.List;

@RestController
@RequestMapping("/api/passwords")
public class PasswordsController {

    private PasswordsService passwordsService;

    public PasswordsController(PasswordsService passwordsService){
        super();
        this.passwordsService = passwordsService;
    }

    // POST passwords REST API <- not fully implemented yet
    //@PostMapping
    //public ResponseEntity<Passwords> savePasswordsData(@RequestBody Passwords passwords){
    //    return new ResponseEntity<Passwords>(passwordsService.savePassword(passwords), HttpStatus.CREATED);
    //}

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
