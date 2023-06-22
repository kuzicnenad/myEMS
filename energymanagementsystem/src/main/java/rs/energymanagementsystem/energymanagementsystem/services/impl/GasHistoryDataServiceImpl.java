package rs.energymanagementsystem.energymanagementsystem.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.energymanagementsystem.energymanagementsystem.entities.GasHistoryData;
import rs.energymanagementsystem.energymanagementsystem.exception.ResourceNotFoundException;
import rs.energymanagementsystem.energymanagementsystem.repositories.GasHistoryDataRepository;
import rs.energymanagementsystem.energymanagementsystem.services.GasHistoryDataService;

import java.util.List;

@Service
public class GasHistoryDataServiceImpl implements GasHistoryDataService {

    private GasHistoryDataRepository gasHistoryDataRepository;

    public  GasHistoryDataServiceImpl(GasHistoryDataRepository gasHistoryDataRepository){
        this.gasHistoryDataRepository = gasHistoryDataRepository;
    }

    @Override
    public List<GasHistoryData> getAllGasHistoryData() {
        return gasHistoryDataRepository.findAll();
    }

    @Override
    public GasHistoryData getGasHistoryDataById(Integer hist_data_id) {
        return gasHistoryDataRepository.findById(hist_data_id).orElseThrow(() ->
                new ResourceNotFoundException("Gas History Data", "hist_data_id", hist_data_id));
    }

    @Override
    public Page<GasHistoryData> getHistoryDataGas(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.gasHistoryDataRepository.findAll(pageable);
    }
}
