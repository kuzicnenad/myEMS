package rs.energymanagementsystem.energymanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rs.energymanagementsystem.energymanagementsystem.entities.WaterHistoryData;
import rs.energymanagementsystem.energymanagementsystem.repositories.WaterHistoryDataRepository;

@RestController
@RequestMapping
public class WaterHistoryDataController {
    @Autowired // This is to get the bean called repository which is auto-generated. Used to handle the data
    private WaterHistoryDataRepository waterHistoryDataRepository;

    public WaterHistoryDataController(WaterHistoryDataRepository repository){
        this.waterHistoryDataRepository = repository;
    }

    @CrossOrigin
    @GetMapping(path = "/waterHistoryData")
    public @ResponseBody Iterable<WaterHistoryData> getAll(){
        // Returns a JSON or XML with waterHistoryData
        return waterHistoryDataRepository.findAll();
    }

    @CrossOrigin
    @GetMapping(path = "/waterHistoryData/{hist_data_id}")
    public WaterHistoryData getWaterHistoryData(@PathVariable Integer hist_data_id){
        return  waterHistoryDataRepository.findById(hist_data_id).orElse(null);
    }

    @CrossOrigin
    @PostMapping("/waterHistoryData")
    public WaterHistoryData createWaterHistoryData(@RequestBody WaterHistoryData waterHistoryData) {
        return waterHistoryDataRepository.save(waterHistoryData);
    }

    @CrossOrigin
    @DeleteMapping("/waterHistoryData/{hist_data_id}")
    public boolean deleteWaterHistoryData(@PathVariable Integer hist_data_id) {
        waterHistoryDataRepository.deleteById(hist_data_id);
        return true;
    }

    @CrossOrigin
    @PutMapping("/waterHistoryData/{hist_data_id}")
    public WaterHistoryData updateWaterHistoryData(@PathVariable Integer hist_data_id, @RequestBody WaterHistoryData waterHistoryData) {
        return waterHistoryDataRepository.save(waterHistoryData);
    }
    // hist_data_id
    // water_consumption
    // date

}
