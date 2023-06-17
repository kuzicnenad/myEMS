package rs.energymanagementsystem.energymanagementsystem.services;

import rs.energymanagementsystem.energymanagementsystem.entities.GasHistoryData;

import java.util.List;

public interface GasHistoryDataService {
    List<GasHistoryData> getAllGasHistoryData();
    GasHistoryData getGasHistoryDataById(Integer hist_data_id);
}
