package rs.energymanagementsystem.energymanagementsystem.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.energymanagementsystem.energymanagementsystem.entities.GasLiveData;
import rs.energymanagementsystem.energymanagementsystem.exceptions.ResourceNotFoundException;
import rs.energymanagementsystem.energymanagementsystem.repositories.GasLiveDataRepository;
import rs.energymanagementsystem.energymanagementsystem.services.GasLiveDataService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GasLiveDataServiceImpl implements GasLiveDataService {

    private final GasLiveDataRepository gasLiveDataRepository;

    /** ---------------------------------------------------------------------------------------
     * - Get all live data records. valuable for GET APIs
     * - Get live data record by ID, valuable for CRUD APIs
     * - Get latest(last 10) records, used on liveData html
     --------------------------------------------------------------------------------------- **/
    @Override
    public List<GasLiveData> getAllGasLiveData() {
        return gasLiveDataRepository.findAll();
    }

    @Override
    public GasLiveData getGasLiveDataById(Integer liveDataId) {
        return gasLiveDataRepository.findById(liveDataId).orElseThrow(() ->
                new ResourceNotFoundException("Gas Live Data","liveDataId",liveDataId));
    }
    @Override
    public List<GasLiveData> getLastData() {
        return gasLiveDataRepository.getLastData();
    }
}
