package rs.energymanagementsystem.energymanagementsystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.energymanagementsystem.energymanagementsystem.entities.Users;
import rs.energymanagementsystem.energymanagementsystem.services.UsersService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private UsersService usersService;

    public UsersController(UsersService usersService){
        super();
        this.usersService = usersService;
    }


    // POST users REST API
    @PostMapping
    public ResponseEntity<Users> saveAlarmData(@RequestBody Users users){
        return new ResponseEntity<Users>(usersService.saveUser(users), HttpStatus.CREATED);
    }

    // GET all users REST API
    @GetMapping
    public List<Users> getAllUsers(){
        return usersService.getAllUsers();
    }

    // GET by ID user REST API
    // http://localhost:8080/api/users/user_id(number)
    @GetMapping("{user_id}")
    public ResponseEntity<Users> getUserById(@PathVariable ("user_id") Integer user_id){
        return new ResponseEntity<Users>(usersService.getUserById(user_id), HttpStatus.OK);
    }

    // UPDATE by ID alarmData REST API
    // http://localhost:8080/api/alarmData/alarm_id(number)
    @PutMapping("{user_id}")
    public ResponseEntity<Users> updateAlarmDate(@PathVariable ("user_id") Integer user_id
            ,@RequestBody Users users){
        return new ResponseEntity<Users>(usersService.updateUsers(users, user_id), HttpStatus.OK);
    }

    // DELETE by ID alarmData REST API
    @DeleteMapping("{user_id}")
    public ResponseEntity<String> deleteAlarmData(@PathVariable("user_id") Integer user_id){
        usersService.deleteUser(user_id);
        return new ResponseEntity<String>("Alarm data deleted successfully!", HttpStatus.OK);
    }

}
