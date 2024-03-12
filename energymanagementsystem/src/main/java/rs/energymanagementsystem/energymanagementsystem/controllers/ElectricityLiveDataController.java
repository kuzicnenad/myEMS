package rs.energymanagementsystem.energymanagementsystem.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.energymanagementsystem.energymanagementsystem.entities.ElectricityLiveData;
import rs.energymanagementsystem.energymanagementsystem.services.ElectricityLiveDataService;

import java.util.List;

@RestController
@RequestMapping("/api/electricityLiveData")
@RequiredArgsConstructor
public class ElectricityLiveDataController {

    private final ElectricityLiveDataService electricityLiveDataService;

    /** GET latest electricity live data REST API **/
    @GetMapping
    public List<ElectricityLiveData> getAllElectricityLiveData(){
        return electricityLiveDataService.getLastData();
    }

    /** GET by ID electricity live data REST API
      * http://localhost:8080/api/electricityLiveData/histDataId(number) **/
    @GetMapping("{liveDataId}")
    public ResponseEntity<ElectricityLiveData> getElectricityLiveDataById(@PathVariable ("liveDataId") Integer liveDataId){
        return new ResponseEntity<ElectricityLiveData>(electricityLiveDataService.getElectricityLiveDataById(liveDataId), HttpStatus.OK);
    }

}
