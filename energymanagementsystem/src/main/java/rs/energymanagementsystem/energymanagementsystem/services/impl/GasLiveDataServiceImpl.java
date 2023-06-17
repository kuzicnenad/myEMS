package rs.energymanagementsystem.energymanagementsystem.services.impl;

import org.springframework.stereotype.Service;
import rs.energymanagementsystem.energymanagementsystem.entities.GasLiveData;
import rs.energymanagementsystem.energymanagementsystem.exception.ResourceNotFoundException;
import rs.energymanagementsystem.energymanagementsystem.repositories.GasLiveDataRepository;
import rs.energymanagementsystem.energymanagementsystem.services.GasLiveDataService;

import java.util.List;

@Service
public class GasLiveDataServiceImpl implements GasLiveDataService {

    private GasLiveDataRepository gasLiveDataRepository;

    public  GasLiveDataServiceImpl(GasLiveDataRepository gasLiveDataRepository){
        this.gasLiveDataRepository = gasLiveDataRepository;
    }

    @Override
    public List<GasLiveData> getAllGasLiveData() {
        return gasLiveDataRepository.findAll();
    }

    @Override
    public GasLiveData getGasLiveDataById(Integer live_data_id) {
        return gasLiveDataRepository.findById(live_data_id).orElseThrow(() ->
                new ResourceNotFoundException("Gas Live Data","live_data_id",live_data_id));
    }
    @Override
    public List<GasLiveData> getLastData() {
        return gasLiveDataRepository.getLastData();
    }
}
