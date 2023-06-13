package rs.energymanagementsystem.energymanagementsystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.energymanagementsystem.energymanagementsystem.entities.ElectricityLiveData;
import rs.energymanagementsystem.energymanagementsystem.services.ElectricityLiveDataService;

import java.util.List;

@RestController
@RequestMapping("/api/electricityLiveData")
public class ElectricityLiveDataController {

    private ElectricityLiveDataService electricityLiveDataService;

    public ElectricityLiveDataController(ElectricityLiveDataService electricityLiveDataService) {
        super();
        this.electricityLiveDataService = electricityLiveDataService;
    }

    // GET all electricity live data REST API
    @GetMapping
    public List<ElectricityLiveData> getAllElectricityLiveData(){
        return electricityLiveDataService.getAllElectricityLiveData();
    }

    // GET by ID electricity live data REST API
    // http://localhost:8080/api/electricityHistoryData/hist_data_id(number)
    @GetMapping("{live_data_id}")
    public ResponseEntity<ElectricityLiveData> getElectricityLiveDataById(@PathVariable ("live_data_id") Integer live_data_id){
        return new ResponseEntity<ElectricityLiveData>(electricityLiveDataService.getElectricityLiveDataById(live_data_id), HttpStatus.OK);
    }

}
