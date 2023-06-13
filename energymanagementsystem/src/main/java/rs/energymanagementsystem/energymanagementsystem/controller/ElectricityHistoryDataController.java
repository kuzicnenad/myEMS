package rs.energymanagementsystem.energymanagementsystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.energymanagementsystem.energymanagementsystem.entities.ElectricityHistoryData;
import rs.energymanagementsystem.energymanagementsystem.services.ElectricityHistoryDataService;

import java.util.List;

@RestController
@RequestMapping("/api/electricityHistoryData")
public class ElectricityHistoryDataController {

    private ElectricityHistoryDataService electricityHistoryDataService;

    public ElectricityHistoryDataController(ElectricityHistoryDataService electricityHistoryDataService){
        super();
        this.electricityHistoryDataService = electricityHistoryDataService;
    }

    // GET all electricity history data REST API
    @GetMapping
    public List<ElectricityHistoryData> getAllElectricityHistoryData(){
        return electricityHistoryDataService.getAllElectricityHistoryData();
    }

    // GET by ID electricity history data REST API
    // http://localhost:8080/api/electricityHistoryData/hist_data_id(number)
    @GetMapping("{hist_data_id}")
    public ResponseEntity<ElectricityHistoryData> getElectricityHistoryDataById(@PathVariable ("hist_data_id") Integer hist_data_id){
        return new ResponseEntity<ElectricityHistoryData>(electricityHistoryDataService.getElectricityHistoryDataById(hist_data_id), HttpStatus.OK);
    }

}
