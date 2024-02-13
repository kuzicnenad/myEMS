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
      * http://localhost:8080/api/devices/device_id(number) **/
    @GetMapping("{device_id}")
    public ResponseEntity<Devices> getDeviceById(@PathVariable ("device_id") Integer device_id){
        return new ResponseEntity<Devices>(devicesService.getDeviceById(device_id), HttpStatus.OK);
    }

    /** UPDATE by ID device REST API
      * http://localhost:8080/api/device/device_id(number) **/
    @PutMapping("{device_id}")
    public ResponseEntity<Devices> updateDevice(@PathVariable ("device_id") Integer device_id
            ,@RequestBody Devices devices){
        return new ResponseEntity<Devices>(devicesService.updateDevice(devices, device_id), HttpStatus.OK);
    }

    /** DELETE by ID device REST API **/
    @DeleteMapping("{device_id}")
    public ResponseEntity<String> deleteDevice(@PathVariable("device_id") Integer device_id){
        devicesService.deleteDevice(device_id);
        return new ResponseEntity<String>("Device deleted successfully!", HttpStatus.OK);
    }

}
