package rs.energymanagementsystem.energymanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rs.energymanagementsystem.energymanagementsystem.entities.GasHistoryData;
import rs.energymanagementsystem.energymanagementsystem.repositories.GasHistoryDataRepository;

@RestController
@RequestMapping
public class GasHistoryDataController {
    @Autowired // This is to get the bean called repository which is auto-generated. Used to handle the data
    private GasHistoryDataRepository gasHistoryDataRepository;

    public GasHistoryDataController(GasHistoryDataRepository repository){
        this.gasHistoryDataRepository = repository;
    }

    @CrossOrigin
    @GetMapping(path = "/gasHistoryData")
    public @ResponseBody Iterable<GasHistoryData> getAll(){
        // Returns a JSON or XML with GasHistoryData
        return gasHistoryDataRepository.findAll();
    }

    @CrossOrigin
    @GetMapping(path = "/gasHistoryData/{hist_data_id}")
    public GasHistoryData getGasHistoryData(@PathVariable Integer hist_data_id){
        return  gasHistoryDataRepository.findById(hist_data_id).orElse(null);
    }

    @CrossOrigin
    @PostMapping("/gasHistoryData")
    public GasHistoryData createGasHistoryData(@RequestBody GasHistoryData gasHistoryData) {
        return gasHistoryDataRepository.save(gasHistoryData);
    }

    @CrossOrigin
    @DeleteMapping("/gasHistoryData/{hist_data_id}")
    public boolean deleteGasHistoryData(@PathVariable Integer hist_data_id) {
        gasHistoryDataRepository.deleteById(hist_data_id);
        return true;
    }

    @CrossOrigin
    @PutMapping("/gasHistoryData/{hist_data_id}")
    public GasHistoryData updateGasHistoryData(@PathVariable Integer hist_data_id, @RequestBody GasHistoryData gasHistoryData) {
        return gasHistoryDataRepository.save(gasHistoryData);
    }
    // hist_data_id
    // gas_consumption
    // date

}
