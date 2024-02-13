package rs.energymanagementsystem.energymanagementsystem.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.energymanagementsystem.energymanagementsystem.entities.AlarmData;
import rs.energymanagementsystem.energymanagementsystem.services.AlarmDataService;

import java.util.List;

@RestController
@RequestMapping("/api/alarmData")
@RequiredArgsConstructor
public class AlarmDataController {

    private final AlarmDataService alarmDataService;

    // POST alarmData REST API
    @PostMapping
    public ResponseEntity<AlarmData> saveAlarmData(@RequestBody AlarmData alarmData){
        return new ResponseEntity<AlarmData>(alarmDataService.saveAlarmData(alarmData), HttpStatus.CREATED);
    }

    // GET all alarmData REST API
    @GetMapping
    public List<AlarmData> getAllAlarmData(){
        return alarmDataService.getAllAlarmData();
    }

    // GET by ID alarmData REST API
    // http://localhost:8080/api/alarmData/alarm_id(number)
    @GetMapping("{alarm_id}")
    public ResponseEntity<AlarmData> getAlarmDataById(@PathVariable ("alarm_id") Integer alarm_id){
        return new ResponseEntity<AlarmData>(alarmDataService.getAlarmDataById(alarm_id), HttpStatus.OK);
    }

    // UPDATE by ID alarmData REST API
    // http://localhost:8080/api/alarmData/alarm_id(number)
    @PutMapping("{alarm_id}")
    public ResponseEntity<AlarmData> updateAlarmDate(@PathVariable ("alarm_id") Integer alarm_id
                                                    ,@RequestBody AlarmData alarmData){
        return new ResponseEntity<AlarmData>(alarmDataService.updateAlarmDate(alarmData, alarm_id), HttpStatus.OK);
    }

    // DELETE by ID alarmData REST API
    @DeleteMapping("{alarm_id}")
    public ResponseEntity<String> deleteAlarmData(@PathVariable("alarm_id") Integer alarm_id){
        alarmDataService.deleteAlarmData(alarm_id);
        return new ResponseEntity<String>("Alarm data deleted successfully!", HttpStatus.OK);
    }

}
