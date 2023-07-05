package rs.energymanagementsystem.energymanagementsystem.services.impl;

import org.springframework.stereotype.Service;
import rs.energymanagementsystem.energymanagementsystem.entities.ElectricityLiveData;
import rs.energymanagementsystem.energymanagementsystem.entities.WaterLiveData;
import rs.energymanagementsystem.energymanagementsystem.exception.ResourceNotFoundException;
import rs.energymanagementsystem.energymanagementsystem.repositories.WaterLiveDataRepository;
import rs.energymanagementsystem.energymanagementsystem.services.WaterLiveDataService;

import java.util.List;

@Service
public class WaterLiveDataServiceImpl implements WaterLiveDataService {

    private WaterLiveDataRepository waterLiveDataRepository;

    public WaterLiveDataServiceImpl(WaterLiveDataRepository waterLiveDataRepository){
        this.waterLiveDataRepository = waterLiveDataRepository;
    }

    /** ---------------------------------------------------------------------------------------
     * - Get all live data records. valuable for GET APIs
     * - Get live data record by ID, valuable for CRUD APIs
     * - Get latest(last 10) records, used on liveData html
     --------------------------------------------------------------------------------------- **/
    @Override
    public List<WaterLiveData> getAllWaterLiveData() {
        return waterLiveDataRepository.findAll();
    }

    @Override
    public WaterLiveData getWaterLiveDataById(Integer live_data_id) {
        return waterLiveDataRepository.findById(live_data_id).orElseThrow(() ->
                new ResourceNotFoundException("Electricity Live Data","live_data_id",live_data_id));
    }
    @Override
    public List<WaterLiveData> getLastData() {
        return waterLiveDataRepository.getLastData();
    }
}
