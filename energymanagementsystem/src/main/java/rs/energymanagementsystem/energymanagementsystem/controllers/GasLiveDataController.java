package rs.energymanagementsystem.energymanagementsystem.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.energymanagementsystem.energymanagementsystem.entities.GasLiveData;
import rs.energymanagementsystem.energymanagementsystem.services.GasLiveDataService;

import java.util.List;

@RestController
@RequestMapping("/api/gasLiveData")
@RequiredArgsConstructor
public class GasLiveDataController {

    private final GasLiveDataService gasLiveDataService;

    /** GET latest electricity live data REST API **/
    @GetMapping
    public List<GasLiveData> getAllGasLiveData(){
        return gasLiveDataService.getLastData();
    }

    /** GET by ID electricity live data REST API
      * http://localhost:8080/api/gasHistoryData/histDataId(number) **/
    @GetMapping("{liveDataId}")
    public ResponseEntity<GasLiveData> getElectricityLiveDataById(@PathVariable ("liveDataId") Integer liveDataId){
        return new ResponseEntity<GasLiveData>(gasLiveDataService.getGasLiveDataById(liveDataId), HttpStatus.OK);
    }

}
