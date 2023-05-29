package rs.energymanagementsystem.energymanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import rs.energymanagementsystem.energymanagementsystem.entities.ElectricityLiveData;
import rs.energymanagementsystem.energymanagementsystem.repositories.ElectricityLiveDataRepository;

@RestController
@RequestMapping
public class ElectricityLiveDataController {

    @Autowired // This is to get the bean called repository which is auto-generated. Used to handle the data
    private ElectricityLiveDataRepository electricityLiveDataRepository;

    public ElectricityLiveDataController(ElectricityLiveDataRepository repository) {
        this.electricityLiveDataRepository = repository;
    }

    @CrossOrigin
    @GetMapping(path = "/electricityLiveData")
    public @ResponseBody Iterable<ElectricityLiveData> getAll(){
        // Returns a JSON or XML with electricity live data
        return electricityLiveDataRepository.findAll();
    }

    @CrossOrigin
    @GetMapping(path = "/electricityLiveData/{live_data_id}")
    public ElectricityLiveData getElectricityLiveData(@PathVariable Integer live_data_id){
        return  electricityLiveDataRepository.findById(live_data_id).orElse(null);
    }

    @CrossOrigin
    @PostMapping("/electricityLiveData")
    public ElectricityLiveData createElectricityLiveData(@RequestBody ElectricityLiveData electricityLiveData) {
        return electricityLiveDataRepository.save(electricityLiveData);
    }

    @CrossOrigin
    @DeleteMapping("/electricityLiveData/{live_data_id}")
    public boolean deleteElectricityLiveData(@PathVariable Integer live_data_id) {
        electricityLiveDataRepository.deleteById(live_data_id);
        return true;
    }

    @CrossOrigin
    @PutMapping("/electricityLiveData/{live_data_id}")
    public ElectricityLiveData updateElectricityLiveData(@PathVariable Integer live_data_id, @RequestBody ElectricityLiveData electricityLiveData) {
        return electricityLiveDataRepository.save(electricityLiveData);
    }


    // live_data_id
    // consumption
    // start_time
    // end_time
    // handshake

}
