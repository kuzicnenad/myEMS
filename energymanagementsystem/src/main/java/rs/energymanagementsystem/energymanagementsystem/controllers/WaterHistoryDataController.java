package rs.energymanagementsystem.energymanagementsystem.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.energymanagementsystem.energymanagementsystem.entities.WaterHistoryData;
import rs.energymanagementsystem.energymanagementsystem.services.WaterHistoryDataService;

import java.util.List;

@RestController
@RequestMapping("/api/waterHistoryData")
@RequiredArgsConstructor
public class WaterHistoryDataController {

    private final WaterHistoryDataService waterHistoryDataService;

    /** GET all electricity history data REST API **/
    @GetMapping
    public List<WaterHistoryData> getAllWaterHistoryData(){
        return waterHistoryDataService.getAllWaterHistoryData();
    }

    /** GET by ID electricity history data REST API
      * http://localhost:8080/api/electricityHistoryData/histDataId(number) **/
    @GetMapping("{histDataId}")
    public ResponseEntity<WaterHistoryData> getElectricityHistoryDataById(@PathVariable ("histDataId") Integer histDataId){
        return new ResponseEntity<WaterHistoryData>(waterHistoryDataService.getWaterHistoryDataById(histDataId), HttpStatus.OK);
    }

}
