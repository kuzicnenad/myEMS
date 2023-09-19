package rs.energymanagementsystem.energymanagementsystem.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.energymanagementsystem.energymanagementsystem.entities.WaterLiveData;
import rs.energymanagementsystem.energymanagementsystem.services.WaterLiveDataService;

import java.util.List;

@RestController
@RequestMapping("/api/waterLiveData")
public class WaterLiveDataController {

    private WaterLiveDataService waterLiveDataService;

    public WaterLiveDataController(WaterLiveDataService waterLiveDataService) {
        super();
        this.waterLiveDataService = waterLiveDataService;
    }

    // GET latest electricity live data REST API
    @GetMapping
    public List<WaterLiveData> getAllWaterLiveData(){
        return waterLiveDataService.getLastData();
    }

    // GET by ID water live data REST API
    // http://localhost:8080/api/waterLiveData/hist_data_id(number)
    @GetMapping("{live_data_id}")
    public ResponseEntity<WaterLiveData> getWaterLiveDataById(@PathVariable ("live_data_id") Integer live_data_id){
        return new ResponseEntity<WaterLiveData>(waterLiveDataService.getWaterLiveDataById(live_data_id), HttpStatus.OK);
    }

}
