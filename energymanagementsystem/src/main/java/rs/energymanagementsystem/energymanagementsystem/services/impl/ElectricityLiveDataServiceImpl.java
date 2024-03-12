package rs.energymanagementsystem.energymanagementsystem.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.energymanagementsystem.energymanagementsystem.entities.ElectricityLiveData;
import rs.energymanagementsystem.energymanagementsystem.exceptions.ResourceNotFoundException;
import rs.energymanagementsystem.energymanagementsystem.repositories.ElectricityLiveDataRepository;
import rs.energymanagementsystem.energymanagementsystem.services.ElectricityLiveDataService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ElectricityLiveDataServiceImpl implements ElectricityLiveDataService {

    private final ElectricityLiveDataRepository electricityLiveDataRepository;

    /** ---------------------------------------------------------------------------------------
     * - Get all live data records. valuable for GET APIs
     * - Get live data record by ID, valuable for CRUD APIs
     * - Get latest(last 10) records, used on liveData html
     --------------------------------------------------------------------------------------- **/
    @Override
    public List<ElectricityLiveData> getAllElectricityLiveData() {
        return electricityLiveDataRepository.findAll();
    }

    @Override
    public ElectricityLiveData getElectricityLiveDataById(Integer liveDataId) {
        return electricityLiveDataRepository.findById(liveDataId).orElseThrow(() ->
                new ResourceNotFoundException("Electricity Live Data","liveDataId",liveDataId));
    }

    @Override
    public List<ElectricityLiveData> getLastData() {
        return electricityLiveDataRepository.getLastData();
    }

}
