package rs.energymanagementsystem.energymanagementsystem.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.energymanagementsystem.energymanagementsystem.entities.WaterHistoryData;
import rs.energymanagementsystem.energymanagementsystem.services.WaterHistoryDataService;

import java.util.List;

@RestController
@RequestMapping("/api/waterHistoryData")
public class WaterHistoryDataController {

    private WaterHistoryDataService waterHistoryDataService;

    public WaterHistoryDataController(WaterHistoryDataService waterHistoryDataService){
        super();
        this.waterHistoryDataService = waterHistoryDataService;
    }

    // GET all electricity history data REST API
    @GetMapping
    public List<WaterHistoryData> getAllWaterHistoryData(){
        return waterHistoryDataService.getAllWaterHistoryData();
    }

    // GET by ID electricity history data REST API
    // http://localhost:8080/api/electricityHistoryData/hist_data_id(number)
    @GetMapping("{hist_data_id}")
    public ResponseEntity<WaterHistoryData> getElectricityHistoryDataById(@PathVariable ("hist_data_id") Integer hist_data_id){
        return new ResponseEntity<WaterHistoryData>(waterHistoryDataService.getWaterHistoryDataById(hist_data_id), HttpStatus.OK);
    }

}
