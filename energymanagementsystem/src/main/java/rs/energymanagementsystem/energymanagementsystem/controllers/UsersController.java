package rs.energymanagementsystem.energymanagementsystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rs.energymanagementsystem.energymanagementsystem.entities.User;
import rs.energymanagementsystem.energymanagementsystem.exception.ResourceNotFoundException;
import rs.energymanagementsystem.energymanagementsystem.services.CustomUserDetailsService;

import java.util.List;

@Controller
public class UsersController {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    // POST users REST API
    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user){
        return new ResponseEntity<User>(customUserDetailsService.saveUser(user), HttpStatus.CREATED);
    }

    // GET all users REST API
    @GetMapping("/api/user")
    public String getAllUsers(Model model){
        List<User> usersEntityList = customUserDetailsService.getAllUsers();
        model.addAttribute("usersList", usersEntityList);

        return "index";
    }

    // GET by ID user REST API
    // http://localhost:8080/api/users/user_id(number)
    @GetMapping("/api/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable ("id") Long id){
        return new ResponseEntity<User>(customUserDetailsService.getUserById(id), HttpStatus.OK);
    }

    // UPDATE by ID user REST API
    // http://localhost:8080/api/alarmData/alarm_id(number)
    @PutMapping("/api/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable ("id") Long id
            , @RequestBody User user){
        return new ResponseEntity<User>(customUserDetailsService.updateUser(user, id), HttpStatus.OK);
    }

    // DELETE by ID alarmData REST API
    @DeleteMapping("/api/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id){
        customUserDetailsService.deleteUser(id);
        return new ResponseEntity<String>("User data deleted successfully!", HttpStatus.OK);
    }

}
