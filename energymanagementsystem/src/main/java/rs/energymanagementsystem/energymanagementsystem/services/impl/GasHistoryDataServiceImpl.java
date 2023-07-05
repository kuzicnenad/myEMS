package rs.energymanagementsystem.energymanagementsystem.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import rs.energymanagementsystem.energymanagementsystem.entities.GasHistoryData;
import rs.energymanagementsystem.energymanagementsystem.exception.ResourceNotFoundException;
import rs.energymanagementsystem.energymanagementsystem.repositories.GasHistoryDataRepository;
import rs.energymanagementsystem.energymanagementsystem.services.GasHistoryDataService;

import java.util.Date;
import java.util.List;

@Service
public class GasHistoryDataServiceImpl implements GasHistoryDataService {

    private GasHistoryDataRepository gasHistoryDataRepository;

    public  GasHistoryDataServiceImpl(GasHistoryDataRepository gasHistoryDataRepository){
        this.gasHistoryDataRepository = gasHistoryDataRepository;
    }

    /** ---------------------------------------------------------------------------------------
     * - Get all history data records. valuable for GET APIs
     * - Get history data record by ID, valuable for CRUD APIs
     * - Service for pagination of history records
     --------------------------------------------------------------------------------------- **/
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
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by("date").descending());
        return this.gasHistoryDataRepository.findAll(pageable);
    }

    /** ---------------------------------------------------------------------------------------
     * Services used to call native query repos
     * and generate valuable data information:
     *      -> Maximum recorded value and date of record
     *      -> Average recorded value and date of first and last record
     *      -> Minimum recorded value and date of record
     --------------------------------------------------------------------------------------- **/
    @Override
    public Integer getGasMaxValue() {
        return gasHistoryDataRepository.getMaxValue();
    }

    @Override
    public Date getGasMaxValueDate() {
        return gasHistoryDataRepository.getMaxValueDate();
    }

    @Override
    public Integer getGasAvgValue() {
        return gasHistoryDataRepository.getAvgValue();
    }

    @Override
    public Date getFirstDate() {
        return gasHistoryDataRepository.getFirstDate();
    }

    @Override
    public Date getLastDate() {
        return gasHistoryDataRepository.getLastDate();
    }

    @Override
    public Integer getGasMinValue() {
        return gasHistoryDataRepository.getMinValue();
    }
    @Override
    public Date getGasMinValueDate() {
        return getGasMaxValueDate();
    }

}
