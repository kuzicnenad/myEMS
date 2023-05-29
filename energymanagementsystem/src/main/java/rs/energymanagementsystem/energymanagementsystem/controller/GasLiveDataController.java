package rs.energymanagementsystem.energymanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rs.energymanagementsystem.energymanagementsystem.entities.GasLiveData;
import rs.energymanagementsystem.energymanagementsystem.repositories.GasLiveDataRepository;

@RestController
@RequestMapping
public class GasLiveDataController {
    @Autowired // This is to get the bean called repository which is auto-generated. Used to handle the data
    private GasLiveDataRepository gasLiveDataRepository;

    public GasLiveDataController(GasLiveDataRepository repository) {
        this.gasLiveDataRepository = repository;
    }

    @CrossOrigin
    @GetMapping(path = "/gasLiveData")
    public @ResponseBody Iterable<GasLiveData> getAll(){
        // Returns a JSON or XML with GasLiveData
        return gasLiveDataRepository.findAll();
    }

    @CrossOrigin
    @GetMapping(path = "/gasLiveData/{live_data_id}")
    public GasLiveData getGasLiveData(@PathVariable Integer live_data_id){
        return  gasLiveDataRepository.findById(live_data_id).orElse(null);
    }

    @CrossOrigin
    @PostMapping("/gasLiveData")
    public GasLiveData createGasLiveData(@RequestBody GasLiveData gasLiveData) {
        return gasLiveDataRepository.save(gasLiveData);
    }

    @CrossOrigin
    @DeleteMapping("/gasLiveData/{live_data_id}")
    public boolean deleteGasLiveData(@PathVariable Integer live_data_id) {
        gasLiveDataRepository.deleteById(live_data_id);
        return true;
    }

    @CrossOrigin
    @PutMapping("/gasLiveData/{live_data_id}")
    public GasLiveData updateGasLiveData(@PathVariable Integer live_data_id, @RequestBody GasLiveData gasLiveData) {
        return gasLiveDataRepository.save(gasLiveData);
    }
    // live_data_id
    // consumption
    // start_time
    // end_time
    // handshake

}
