package rs.energymanagementsystem.energymanagementsystem.services;

import org.springframework.stereotype.Repository;
import rs.energymanagementsystem.energymanagementsystem.entities.ElectricityHistoryData;

import java.util.List;

@Repository
public interface ElectricityHistoryDataService {
    List<ElectricityHistoryData> getAllElectricityHistoryData();
    ElectricityHistoryData getElectricityHistoryDataById(Integer hist_data_id);
    List<ElectricityHistoryData> getHistoryData();
}
