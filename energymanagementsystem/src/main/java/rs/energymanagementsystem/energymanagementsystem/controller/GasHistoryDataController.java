package rs.energymanagementsystem.energymanagementsystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.energymanagementsystem.energymanagementsystem.entities.GasHistoryData;
import rs.energymanagementsystem.energymanagementsystem.services.GasHistoryDataService;

import java.util.List;

@RestController
@RequestMapping("/api/gasHistoryData")
public class GasHistoryDataController {
    private GasHistoryDataService gasHistoryDataService;

    public GasHistoryDataController(GasHistoryDataService gasHistoryDataService){
        super();
        this.gasHistoryDataService = gasHistoryDataService;
    }

    // GET all gas history data REST API
    @GetMapping
    public List<GasHistoryData> getAllGasHistoryData(){
        return gasHistoryDataService.getAllGasHistoryData();
    }

    // GET by ID gas history data REST API
    // http://localhost:8080/api/electricityHistoryData/hist_data_id(number)
    @GetMapping("{hist_data_id}")
    public ResponseEntity<GasHistoryData> getGasHistoryDataById(@PathVariable ("hist_data_id") Integer hist_data_id){
        return new ResponseEntity<GasHistoryData>(gasHistoryDataService.getGasHistoryDataById(hist_data_id), HttpStatus.OK);
    }

}
