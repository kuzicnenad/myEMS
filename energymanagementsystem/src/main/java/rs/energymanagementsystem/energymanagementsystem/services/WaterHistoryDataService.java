package rs.energymanagementsystem.energymanagementsystem.services;

import org.springframework.data.domain.Page;
import rs.energymanagementsystem.energymanagementsystem.entities.WaterHistoryData;

import java.util.List;

public interface WaterHistoryDataService {
    List<WaterHistoryData> getAllWaterHistoryData();
    WaterHistoryData getWaterHistoryDataById(Integer hist_data_id);
    Page<WaterHistoryData> getHistoryDataWater(int pageNo, int pageSize);
}
