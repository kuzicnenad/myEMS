package rs.energymanagementsystem.energymanagementsystem.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.energymanagementsystem.energymanagementsystem.entities.ElectricityHistoryData;
import rs.energymanagementsystem.energymanagementsystem.exception.ResourceNotFoundException;
import rs.energymanagementsystem.energymanagementsystem.repositories.ElectricityHistoryDataRepository;
import rs.energymanagementsystem.energymanagementsystem.services.ElectricityHistoryDataService;

import java.util.List;

@Service
public class ElectricityHistoryDataServiceImpl implements ElectricityHistoryDataService {

    private ElectricityHistoryDataRepository electricityHistoryDataRepository;

    public  ElectricityHistoryDataServiceImpl(ElectricityHistoryDataRepository electricityHistoryDataRepository){
        this.electricityHistoryDataRepository = electricityHistoryDataRepository;
    }

    @Override
    public List<ElectricityHistoryData> getAllElectricityHistoryData() {
        return electricityHistoryDataRepository.findAll();
    }

    @Override
    public ElectricityHistoryData getElectricityHistoryDataById(Integer hist_data_id) {
        return electricityHistoryDataRepository.findById(hist_data_id).orElseThrow(() ->
                new ResourceNotFoundException("Electricity History Data", "hist_data_id", hist_data_id));
    }

    @Override
    public Page<ElectricityHistoryData> getHistoryDataElectricity(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.electricityHistoryDataRepository.findAll(pageable);
    }
}
