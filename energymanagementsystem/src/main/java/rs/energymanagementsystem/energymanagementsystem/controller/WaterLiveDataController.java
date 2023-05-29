package rs.energymanagementsystem.energymanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rs.energymanagementsystem.energymanagementsystem.entities.WaterLiveData;
import rs.energymanagementsystem.energymanagementsystem.repositories.WaterLiveDataRepository;

@RestController
@RequestMapping
public class WaterLiveDataController {

    @Autowired // This is to get the bean called repository which is auto-generated. Used to handle the data
    private WaterLiveDataRepository waterLiveDataRepository;

    public WaterLiveDataController(WaterLiveDataRepository repository) {
        this.waterLiveDataRepository = repository;
    }

    @CrossOrigin
    @GetMapping(path = "/waterLiveData")
    public @ResponseBody Iterable<WaterLiveData> getAll(){
        // Returns a JSON or XML with WaterLiveData
        return waterLiveDataRepository.findAll();
    }

    @CrossOrigin
    @GetMapping(path = "/waterLiveData/{live_data_id}")
    public WaterLiveData getWaterLiveData(@PathVariable Integer live_data_id){
        return  waterLiveDataRepository.findById(live_data_id).orElse(null);
    }

    @CrossOrigin
    @PostMapping("/waterLiveData")
    public WaterLiveData createWaterLiveData(@RequestBody WaterLiveData waterLiveData) {
        return waterLiveDataRepository.save(waterLiveData);
    }

    @CrossOrigin
    @DeleteMapping("/waterLiveData/{live_data_id}")
    public boolean deleteWaterLiveData(@PathVariable Integer live_data_id) {
        waterLiveDataRepository.deleteById(live_data_id);
        return true;
    }

    @CrossOrigin
    @PutMapping("/waterLiveData/{live_data_id}")
    public WaterLiveData updateWaterLiveData(@PathVariable Integer id, @RequestBody WaterLiveData waterLiveData) {
        return waterLiveDataRepository.save(waterLiveData);
    }
    // live_data_id
    // consumption
    // start_time
    // end_time
    // handshake

}
