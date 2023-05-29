package rs.energymanagementsystem.energymanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rs.energymanagementsystem.energymanagementsystem.entities.Passwords;
import rs.energymanagementsystem.energymanagementsystem.repositories.PasswordsRepository;

@RequestMapping
@RestController
public class PasswordsController {

    @Autowired // This is to get the bean called repository which is auto-generated. Used to handle the data
    private PasswordsRepository passwordsRepository;

    public PasswordsController(PasswordsRepository repository){
        this.passwordsRepository = repository;
    }

    @CrossOrigin
    @GetMapping(path = "/passwords")
    public @ResponseBody Iterable<Passwords> getAll(){
        // Returns a JSON or XML with Passwords
        return passwordsRepository.findAll();
    }

    @CrossOrigin
    @GetMapping(path = "/passwords/{hash_algorithm_id}")
    public Passwords getPasswords(@PathVariable Integer hash_algorithm_id){
        return  passwordsRepository.findById(hash_algorithm_id).orElse(null);
    }

    @CrossOrigin
    @PostMapping("/passwords")
    public Passwords createPasswords(@RequestBody Passwords passwords) {
        return passwordsRepository.save(passwords);
    }

    @CrossOrigin
    @DeleteMapping("/passwords/{hash_algorithm_id}")
    public boolean deleteUser(@PathVariable Integer hash_algorithm_id) {
        passwordsRepository.deleteById(hash_algorithm_id);
        return true;
    }

    @CrossOrigin
    @PutMapping("/passwords/{hash_algorithm_id}")
    public Passwords updatePasswords(@PathVariable Integer hash_algorithm_id, @RequestBody Passwords passwords) {
        return passwordsRepository.save(passwords);
    }
    // hash_algorithm_id
    // user_id
    // password_hash
    // time_stamp

}
