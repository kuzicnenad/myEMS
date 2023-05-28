package rs.energymanagementsystem.energymanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rs.energymanagementsystem.energymanagementsystem.entities.Users;
import rs.energymanagementsystem.energymanagementsystem.repositories.UsersRepository;

@RestController
@RequestMapping
public class UsersController {

    @Autowired // This is to get the bean called repository which is auto-generated. Used to handle the data
    private UsersRepository usersRepository;

    public UsersController(UsersRepository repository){
        this.usersRepository = repository;
    }


    @CrossOrigin
    @GetMapping(path = "/users")
    public @ResponseBody Iterable<Users> getAll(){
        // Returns a JSON or XML with users
        return usersRepository.findAll();
    }

    @CrossOrigin
    @GetMapping(path = "/users/{user_id}")
    public Users getUsers(@PathVariable Integer user_id){
        return  usersRepository.findById(user_id).orElse(null);
    }

    @CrossOrigin
    @PostMapping("/users")
    public Users createUser(@RequestBody Users users) {
        return usersRepository.save(users);
    }

    @CrossOrigin
    @DeleteMapping("/users/{id}")
    public boolean deleteUser(@PathVariable Integer id) {
        usersRepository.deleteById(id);
        return true;
    }

    @CrossOrigin
    @PutMapping("/users/{id}")
    public Users updateUser(@PathVariable Integer id, @RequestBody Users users) {
        return usersRepository.save(users);
    }

}
