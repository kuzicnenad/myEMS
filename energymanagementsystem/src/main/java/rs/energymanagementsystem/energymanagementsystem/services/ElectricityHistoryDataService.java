package rs.energymanagementsystem.energymanagementsystem.services;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import rs.energymanagementsystem.energymanagementsystem.entities.ElectricityHistoryData;

import java.util.Date;
import java.util.List;

@Repository
public interface ElectricityHistoryDataService {

    /** ---------------------------------------------------------------------------------------
     * - Get all history data records. valuable for GET APIs
     * - Get history data record by ID, valuable for CRUD APIs
     * - Service for pagination of history records
     --------------------------------------------------------------------------------------- **/
    List<ElectricityHistoryData> getAllElectricityHistoryData();
    ElectricityHistoryData getElectricityHistoryDataById(Integer hist_data_id);
    Page<ElectricityHistoryData> getHistoryDataElectricity(int pageNo, int pageSize);

    /** ---------------------------------------------------------------------------------------
     * Services used to call native query repos
     * and generate valuable data information:
     *      -> Maximum recorded value and date of record
     *      -> Average recorded value and date of first and last record
     *      -> Minimum recorded value and date of record
     --------------------------------------------------------------------------------------- **/
    Integer getElectricityMaxValue();

    Date getElectricityMaxValueDate();

    Integer getElectricityAvgValue();

    Integer getElectricityMinValue();

    Date getFirstDate();
    Date getLastDate();

    Date getElectricityMinValueDate();

}
