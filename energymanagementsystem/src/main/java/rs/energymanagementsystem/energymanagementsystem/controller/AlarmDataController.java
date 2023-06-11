package rs.energymanagementsystem.energymanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.energymanagementsystem.energymanagementsystem.entities.AlarmData;
import rs.energymanagementsystem.energymanagementsystem.repositories.AlarmDataRepository;
import rs.energymanagementsystem.energymanagementsystem.services.AlarmDataService;

@RestController
@RequestMapping
public class AlarmDataController {

    private AlarmDataService alarmDataService;
    @Autowired // This is to get the bean called repository which is auto-generated. Used to handle the data
    private AlarmDataRepository alarmDataRepository;

    public AlarmDataController(AlarmDataService alarmDataService){
        super();
        this.alarmDataService= alarmDataService;
    }

    // Alarm data REST API
    @PostMapping
    @RequestMapping("/api/alarmData")
    public ResponseEntity<AlarmData> saveAlarmData(@RequestBody AlarmData alarmData){
        return new ResponseEntity<AlarmData>(alarmDataService.saveAlarmData(alarmData), HttpStatus.CREATED);
    }

    @CrossOrigin
    @GetMapping(path = "/alarmData")
    public @ResponseBody Iterable<AlarmData> getAll(){
        // Returns a JSON or XML with users
        return alarmDataRepository.findAll();
    }

    @CrossOrigin
    @GetMapping(path = "/alarmData/{alarm_id}")
    public AlarmData getUsers(@PathVariable Integer alarm_id){
        return  alarmDataRepository.findById(alarm_id).orElse(null);
    }

    @CrossOrigin
    @PostMapping("/alarmData")
    public AlarmData createUser(@RequestBody AlarmData alarmData) {
        return alarmDataRepository.save(alarmData);
    }

    @CrossOrigin
    @DeleteMapping("/alarmData/{alarm_id}")
    public boolean deleteAlarmData(@PathVariable Integer alarm_id) {
        alarmDataRepository.deleteById(alarm_id);
        return true;
    }

    @CrossOrigin
    @PutMapping("/alarmData/{alarm_id}")
    public AlarmData updateAlarmData(@PathVariable Integer id, @RequestBody AlarmData alarmData) {
        return alarmDataRepository.save(alarmData);
    }

}
