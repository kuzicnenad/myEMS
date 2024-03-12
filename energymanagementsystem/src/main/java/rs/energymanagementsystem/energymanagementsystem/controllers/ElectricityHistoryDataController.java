package rs.energymanagementsystem.energymanagementsystem.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.energymanagementsystem.energymanagementsystem.entities.ElectricityHistoryData;
import rs.energymanagementsystem.energymanagementsystem.services.ElectricityHistoryDataService;

import java.util.List;

@RestController
@RequestMapping("/api/electricityHistoryData")
@RequiredArgsConstructor
public class ElectricityHistoryDataController {

    private final ElectricityHistoryDataService electricityHistoryDataService;

    /** GET all electricity history data REST API **/
    @GetMapping
    public List<ElectricityHistoryData> getAllElectricityHistoryData(){
        return electricityHistoryDataService.getAllElectricityHistoryData();
    }

    /** GET by ID electricity history data REST API
      * http://localhost:8080/api/electricityHistoryData/histDataId(number) **/
    @GetMapping("{histDataId}")
    public ResponseEntity<ElectricityHistoryData> getElectricityHistoryDataById(@PathVariable ("histDataId") Integer histDataId){
        return new ResponseEntity<ElectricityHistoryData>(electricityHistoryDataService.getElectricityHistoryDataById(histDataId), HttpStatus.OK);
    }

}
