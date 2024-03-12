package rs.energymanagementsystem.energymanagementsystem.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import rs.energymanagementsystem.energymanagementsystem.entities.ElectricityHistoryData;
import rs.energymanagementsystem.energymanagementsystem.exceptions.ResourceNotFoundException;
import rs.energymanagementsystem.energymanagementsystem.repositories.ElectricityHistoryDataRepository;
import rs.energymanagementsystem.energymanagementsystem.services.ElectricityHistoryDataService;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ElectricityHistoryDataServiceImpl implements ElectricityHistoryDataService {

    private final ElectricityHistoryDataRepository electricityHistoryDataRepository;

    /** ---------------------------------------------------------------------------------------
     * - Get all history data records. valuable for GET APIs
     * - Get history data record by ID, valuable for CRUD APIs
     * - Service for pagination of history records
     --------------------------------------------------------------------------------------- **/
    @Override
    public List<ElectricityHistoryData> getAllElectricityHistoryData() {
        return electricityHistoryDataRepository.findAll();
    }

    @Override
    public ElectricityHistoryData getElectricityHistoryDataById(Integer histDataId) {
        return electricityHistoryDataRepository.findById(histDataId).orElseThrow(() ->
                new ResourceNotFoundException("Electricity History Data", "histDataId", histDataId));
    }

    @Override
    public Page<ElectricityHistoryData> getHistoryDataElectricity(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by("date").descending());
        return this.electricityHistoryDataRepository.findAll(pageable);
    }

    /** ---------------------------------------------------------------------------------------
     * Services used to call native query repos
     * and generate valuable data information:
     *      -> Maximum recorded value and date of record
     *      -> Average recorded value and date of first and last record
     *      -> Minimum recorded value and date of record
     --------------------------------------------------------------------------------------- **/
    @Override
    public Integer getElectricityMaxValue() {
        return electricityHistoryDataRepository.getMaxValue();
    }
    @Override
    public Date getElectricityMaxValueDate() {
        return electricityHistoryDataRepository.getMaxValueDate();
    }

    @Override
    public Integer getElectricityAvgValue() {
        return electricityHistoryDataRepository.getAvgValue();
    }

    @Override
    public Date getFirstDate() {
        return electricityHistoryDataRepository.getFirstDate();
    }
    @Override
    public Date getLastDate() {
        return electricityHistoryDataRepository.getLastDate();
    }

    @Override
    public Integer getElectricityMinValue() {
        return electricityHistoryDataRepository.getMinValue();
    }

    @Override
    public Date getElectricityMinValueDate() {
        return electricityHistoryDataRepository.getMinValueDate();
    }


}
