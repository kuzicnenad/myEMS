package rs.energymanagementsystem.energymanagementsystem.services;

import rs.energymanagementsystem.energymanagementsystem.entities.ElectricityHistoryData;

import java.util.List;

public interface ElectricityHistoryDataService {
    List<ElectricityHistoryData> getAllElectricityHistoryData();
    ElectricityHistoryData getElectricityHistoryDataById(Integer hist_data_id);
}
