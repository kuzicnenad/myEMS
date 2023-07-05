package rs.energymanagementsystem.energymanagementsystem.services;

import org.springframework.data.domain.Page;
import rs.energymanagementsystem.energymanagementsystem.entities.WaterHistoryData;

import java.util.Date;
import java.util.List;

public interface WaterHistoryDataService {

    /** ---------------------------------------------------------------------------------------
     * - Get all history data records. valuable for GET APIs
     * - Get history data record by ID, valuable for CRUD APIs
     * - Service for pagination of history records
     --------------------------------------------------------------------------------------- **/
    List<WaterHistoryData> getAllWaterHistoryData();
    WaterHistoryData getWaterHistoryDataById(Integer hist_data_id);
    Page<WaterHistoryData> getHistoryDataWater(int pageNo, int pageSize);

    /** ---------------------------------------------------------------------------------------
     * Services used to call native query repos
     * and generate valuable data information:
     *      -> Maximum recorded value and date of record
     *      -> Average recorded value and date of first and last record
     *      -> Minimum recorded value and date of record
     --------------------------------------------------------------------------------------- **/
    Integer getWaterMaxValue();

    Date getWaterMaxValueDate();

    Integer getWaterAvgValue();

    Date getFirstDate();

    Date getLastDate();
}
