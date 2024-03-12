package rs.energymanagementsystem.energymanagementsystem.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.energymanagementsystem.energymanagementsystem.entities.Devices;
import rs.energymanagementsystem.energymanagementsystem.services.DevicesService;

import java.util.List;

@RestController
@RequestMapping("/api/devices")
@RequiredArgsConstructor
public class DevicesController {

    @Autowired
    private final DevicesService devicesService;

    /** POST alarmData REST API **/
    public ResponseEntity<Devices> saveDevice(@RequestBody Devices devices){
        return new ResponseEntity<Devices>(devicesService.saveDevice(devices), HttpStatus.CREATED);
    }

    /** GET all device REST API **/
    @GetMapping
    public List<Devices> getAllDevices(){
        return devicesService.getAllDevices();
    }

    /** GET by ID device REST API
      * http://localhost:8080/api/devices/deviceId(number) **/
    @GetMapping("{deviceId}")
    public ResponseEntity<Devices> getDeviceById(@PathVariable ("deviceId") Integer deviceId){
        return new ResponseEntity<Devices>(devicesService.getDeviceById(deviceId), HttpStatus.OK);
    }

    /** UPDATE by ID device REST API
      * http://localhost:8080/api/device/deviceId(number) **/
    @PutMapping("{deviceId}")
    public ResponseEntity<Devices> updateDevice(@PathVariable ("deviceId") Integer deviceId
            ,@RequestBody Devices devices){
        return new ResponseEntity<Devices>(devicesService.updateDevice(devices, deviceId), HttpStatus.OK);
    }

    /** DELETE by ID device REST API **/
    @DeleteMapping("{deviceId}")
    public ResponseEntity<String> deleteDevice(@PathVariable("deviceId") Integer deviceId){
        devicesService.deleteDevice(deviceId);
        return new ResponseEntity<String>("Device deleted successfully!", HttpStatus.OK);
    }

}
