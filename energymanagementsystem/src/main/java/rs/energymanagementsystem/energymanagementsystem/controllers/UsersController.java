package rs.energymanagementsystem.energymanagementsystem.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rs.energymanagementsystem.energymanagementsystem.entities.UsersEntity;
import rs.energymanagementsystem.energymanagementsystem.services.UsersService;

import java.util.List;

@Controller
public class UsersController {

    @Autowired
    private UsersService usersService;

    // POST users REST API
    @PostMapping
    public ResponseEntity<UsersEntity> saveAlarmData(@RequestBody UsersEntity usersEntity){
        return new ResponseEntity<UsersEntity>(usersService.saveUser(usersEntity), HttpStatus.CREATED);
    }

    // GET all users REST API
    @GetMapping("/api/users")
    public String getAllUsers(Model model){
        List<UsersEntity> usersEntityList = usersService.getAllUsers();
        model.addAttribute("usersList", usersEntityList);

        return "index";
    }

    // GET by ID user REST API
    // http://localhost:8080/api/users/user_id(number)
    @GetMapping("/api/users/{user_id}")
    public ResponseEntity<UsersEntity> getUserById(@PathVariable ("user_id") Integer user_id){
        return new ResponseEntity<UsersEntity>(usersService.getUserById(user_id), HttpStatus.OK);
    }

    // UPDATE by ID alarmData REST API
    // http://localhost:8080/api/alarmData/alarm_id(number)
    @PutMapping("/api/users/{user_id}")
    public ResponseEntity<UsersEntity> updateAlarmDate(@PathVariable ("user_id") Integer user_id
            , @RequestBody UsersEntity usersEntity){
        return new ResponseEntity<UsersEntity>(usersService.updateUsers(usersEntity, user_id), HttpStatus.OK);
    }

    // DELETE by ID alarmData REST API
    @DeleteMapping("/api/users/{user_id}")
    public ResponseEntity<String> deleteAlarmData(@PathVariable("user_id") Integer user_id){
        usersService.deleteUser(user_id);
        return new ResponseEntity<String>("Alarm data deleted successfully!", HttpStatus.OK);
    }

}
