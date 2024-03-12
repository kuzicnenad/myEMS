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

    /** POST alarmData REST API **/
    @PostMapping
    public ResponseEntity<AlarmData> saveAlarmData(@RequestBody AlarmData alarmData){
        return new ResponseEntity<AlarmData>(alarmDataService.saveAlarmData(alarmData), HttpStatus.CREATED);
    }

    /** GET all alarmData REST API **/
    @GetMapping
    public List<AlarmData> getAllAlarmData(){
        return alarmDataService.getAllAlarmData();
    }

    /** GET by ID alarmData REST API
      * http://localhost:8080/api/alarmData/alarmId(number) **/
    @GetMapping("{alarmId}")
    public ResponseEntity<AlarmData> getAlarmDataById(@PathVariable ("alarmId") Integer alarmId){
        return new ResponseEntity<AlarmData>(alarmDataService.getAlarmDataById(alarmId), HttpStatus.OK);
    }

    /** UPDATE by ID alarmData REST API
      * http://localhost:8080/api/alarmData/alarmId(number) **/
    @PutMapping("{alarmId}")
    public ResponseEntity<AlarmData> updateAlarmDate(@PathVariable ("alarmId") Integer alarmId
                                                    ,@RequestBody AlarmData alarmData){
        return new ResponseEntity<AlarmData>(alarmDataService.updateAlarmDate(alarmData, alarmId), HttpStatus.OK);
    }

    /** DELETE by ID alarmData REST API **/
    @DeleteMapping("{alarmId}")
    public ResponseEntity<String> deleteAlarmData(@PathVariable("alarmId") Integer alarmId){
        alarmDataService.deleteAlarmData(alarmId);
        return new ResponseEntity<String>("Alarm data deleted successfully!", HttpStatus.OK);
    }

}
