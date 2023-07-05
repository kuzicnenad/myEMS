package rs.energymanagementsystem.energymanagementsystem.services.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import rs.energymanagementsystem.energymanagementsystem.entities.WaterHistoryData;
import rs.energymanagementsystem.energymanagementsystem.exception.ResourceNotFoundException;
import rs.energymanagementsystem.energymanagementsystem.repositories.WaterHistoryDataRepository;
import rs.energymanagementsystem.energymanagementsystem.services.WaterHistoryDataService;

import java.util.Date;
import java.util.List;

@Service
public class WaterHistoryDataServiceImpl implements WaterHistoryDataService {

    private WaterHistoryDataRepository waterHistoryDataRepository;

    public WaterHistoryDataServiceImpl(WaterHistoryDataRepository waterHistoryDataRepository){
        this.waterHistoryDataRepository = waterHistoryDataRepository;
    }

    /** ---------------------------------------------------------------------------------------
     * - Get all history data records. valuable for GET APIs
     * - Get history data record by ID, valuable for CRUD APIs
     * - Service for pagination of history records
     --------------------------------------------------------------------------------------- **/
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
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by("date").descending());
        return this.waterHistoryDataRepository.findAll(pageable);
    }

    /** ---------------------------------------------------------------------------------------
     * Services used to call native query repos
     * and generate valuable data information:
     *      -> Maximum recorded value and date of record
     *      -> Average recorded value and date of first and last record
     *      -> Minimum recorded value and date of record
     --------------------------------------------------------------------------------------- **/
    @Override
    public Integer getWaterMaxValue() {
        return waterHistoryDataRepository.getMaxValue();
    }

    @Override
    public Date getWaterMaxValueDate() {
        return waterHistoryDataRepository.getMaxValueDate();
    }

    @Override
    public Integer getWaterAvgValue() {
        return waterHistoryDataRepository.getAvgValue();
    }

    @Override
    public Date getFirstDate() {
        return waterHistoryDataRepository.getFirstDate();
    }

    @Override
    public Date getLastDate() {
        return waterHistoryDataRepository.getLastDate();
    }

    @Override
    public Integer getWaterMinValue() {
        return waterHistoryDataRepository.getMinValue();
    }

    @Override
    public Date getWaterMinValueDate() {
        return waterHistoryDataRepository.getMinValueDate();
    }


}