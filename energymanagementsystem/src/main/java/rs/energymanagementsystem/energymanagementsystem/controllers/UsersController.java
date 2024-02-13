package rs.energymanagementsystem.energymanagementsystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rs.energymanagementsystem.energymanagementsystem.entities.User;
import rs.energymanagementsystem.energymanagementsystem.services.UserDetailsService;

import java.util.List;

@Controller
@RequestMapping("/api/user")
public class UsersController {

    @Autowired
    private UserDetailsService userDetailsService;

    // POST users REST API
    public ResponseEntity<User> saveUser(@RequestBody User user){
        return new ResponseEntity<User>(userDetailsService.saveUser(user), HttpStatus.CREATED);
    }

    // GET all users REST API
    public String getAllUsers(Model model){
        List<User> usersEntityList = userDetailsService.getAllUsers();
        model.addAttribute("usersList", usersEntityList);

        return "index";
    }

    // GET by ID user REST API
    // http://localhost:8080/api/users/user_id(number)
    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable ("id") Long id){
        return new ResponseEntity<User>(userDetailsService.getUserById(id), HttpStatus.OK);
    }

    // UPDATE by ID user REST API
    // http://localhost:8080/api/alarmData/alarm_id(number)
    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable ("id") Long id
            , @RequestBody User user){
        return new ResponseEntity<User>(userDetailsService.updateUser(user, id), HttpStatus.OK);
    }

    // DELETE by ID alarmData REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id){
        userDetailsService.deleteUser(id);
        return new ResponseEntity<String>("User data deleted successfully!", HttpStatus.OK);
    }

}
