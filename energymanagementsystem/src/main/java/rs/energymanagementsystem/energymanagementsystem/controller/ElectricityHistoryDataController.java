package rs.energymanagementsystem.energymanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rs.energymanagementsystem.energymanagementsystem.entities.ElectricityHistoryData;
import rs.energymanagementsystem.energymanagementsystem.repositories.ElectricityHistoryDataRepository;

@RestController
@RequestMapping
public class ElectricityHistoryDataController {

    @Autowired // This is to get the bean called repository which is auto-generated. Used to handle the data
    private ElectricityHistoryDataRepository electricityHistoryDataRepository;

    public ElectricityHistoryDataController(ElectricityHistoryDataRepository repository){
        this.electricityHistoryDataRepository = repository;
    }

    @CrossOrigin
    @GetMapping(path = "/electricityHistoryData")
    public @ResponseBody Iterable<ElectricityHistoryData> getAll(){
        // Returns a JSON or XML with electricity history data
        return electricityHistoryDataRepository.findAll();
    }

    @CrossOrigin
    @GetMapping(path = "/electricityHistoryData/{hist_data_id}")
    public ElectricityHistoryData getElectricityHistoryData(@PathVariable Integer hist_data_id){
        return  electricityHistoryDataRepository.findById(hist_data_id).orElse(null);
    }

    @CrossOrigin
    @PostMapping("/electricityHistoryData")
    public ElectricityHistoryData createElectricityHistoryData(@RequestBody ElectricityHistoryData electricityHistoryData) {
        return electricityHistoryDataRepository.save(electricityHistoryData);
    }

    @CrossOrigin
    @DeleteMapping("/electricityHistoryData/{hist_data_id}")
    public boolean deleteElectricityHistoryData(@PathVariable Integer hist_data_id) {
        electricityHistoryDataRepository.deleteById(hist_data_id);
        return true;
    }

    @CrossOrigin
    @PutMapping("/electricityHistoryData/{hist_data_id}")
    public ElectricityHistoryData updateElectricityHistoryData(@PathVariable Integer hist_data_id, @RequestBody ElectricityHistoryData electricityHistoryData) {
        return electricityHistoryDataRepository.save(electricityHistoryData);
    }

}
