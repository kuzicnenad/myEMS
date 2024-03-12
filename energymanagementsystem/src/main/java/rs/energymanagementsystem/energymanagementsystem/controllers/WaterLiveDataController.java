package rs.energymanagementsystem.energymanagementsystem.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.energymanagementsystem.energymanagementsystem.entities.WaterLiveData;
import rs.energymanagementsystem.energymanagementsystem.services.WaterLiveDataService;

import java.util.List;

@RestController
@RequestMapping("/api/waterLiveData")
@RequiredArgsConstructor
public class WaterLiveDataController {

    private final WaterLiveDataService waterLiveDataService;

    /** GET latest electricity live data REST API **/
    @GetMapping
    public List<WaterLiveData> getAllWaterLiveData(){
        return waterLiveDataService.getLastData();
    }

    /** GET by ID water live data REST API
      * http://localhost:8080/api/waterLiveData/histDataId(number) **/
    @GetMapping("{liveDataId}")
    public ResponseEntity<WaterLiveData> getWaterLiveDataById(@PathVariable ("liveDataId") Integer liveDataId){
        return new ResponseEntity<WaterLiveData>(waterLiveDataService.getWaterLiveDataById(liveDataId), HttpStatus.OK);
    }

}
