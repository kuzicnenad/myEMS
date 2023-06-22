package rs.energymanagementsystem.energymanagementsystem.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.energymanagementsystem.energymanagementsystem.entities.WaterHistoryData;
import rs.energymanagementsystem.energymanagementsystem.exception.ResourceNotFoundException;
import rs.energymanagementsystem.energymanagementsystem.repositories.WaterHistoryDataRepository;
import rs.energymanagementsystem.energymanagementsystem.services.WaterHistoryDataService;

import java.util.List;

@Service
public class WaterHistoryDataServiceImpl implements WaterHistoryDataService {

    private WaterHistoryDataRepository waterHistoryDataRepository;

    public WaterHistoryDataServiceImpl(WaterHistoryDataRepository waterHistoryDataRepository){
        this.waterHistoryDataRepository = waterHistoryDataRepository;
    }

    @Override
    public List<WaterHistoryData> getAllWaterHistoryData() {
        return waterHistoryDataRepository.findAll();
    }

    @Override
    public WaterHistoryData getWaterHistoryDataById(Integer hist_data_id) {
        return waterHistoryDataRepository.findById(hist_data_id).orElseThrow(() ->
                new ResourceNotFoundException("Water History Data", "hist_data_id", hist_data_id));
    }

    @Override
    public Page<WaterHistoryData> getHistoryDataWater(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.waterHistoryDataRepository.findAll(pageable);
    }
}