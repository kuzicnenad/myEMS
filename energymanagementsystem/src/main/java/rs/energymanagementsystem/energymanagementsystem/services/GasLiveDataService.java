package rs.energymanagementsystem.energymanagementsystem.services;

import rs.energymanagementsystem.energymanagementsystem.entities.GasLiveData;

import java.util.List;

public interface GasLiveDataService {
    List<GasLiveData> getAllGasLiveData();
    GasLiveData getGasLiveDataById(Integer live_data_id);
}
