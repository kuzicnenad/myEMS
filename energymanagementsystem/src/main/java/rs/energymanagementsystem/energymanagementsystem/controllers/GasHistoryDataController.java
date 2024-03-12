package rs.energymanagementsystem.energymanagementsystem.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.energymanagementsystem.energymanagementsystem.entities.GasHistoryData;
import rs.energymanagementsystem.energymanagementsystem.services.GasHistoryDataService;

import java.util.List;

@RestController
@RequestMapping("/api/gasHistoryData")
@RequiredArgsConstructor
public class GasHistoryDataController {
    private final GasHistoryDataService gasHistoryDataService;

    /** GET all gas history data REST API **/
    @GetMapping
    public List<GasHistoryData> getAllGasHistoryData(){
        return gasHistoryDataService.getAllGasHistoryData();
    }

    /** GET by ID gas history data REST API
      * http://localhost:8080/api/electricityHistoryData/histDataId(number) **/
    @GetMapping("{histDataId}")
    public ResponseEntity<GasHistoryData> getGasHistoryDataById(@PathVariable ("histDataId") Integer histDataId){
        return new ResponseEntity<GasHistoryData>(gasHistoryDataService.getGasHistoryDataById(histDataId), HttpStatus.OK);
    }

}
