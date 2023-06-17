package rs.energymanagementsystem.energymanagementsystem.services;

import rs.energymanagementsystem.energymanagementsystem.entities.WaterLiveData;

import java.util.List;

public interface WaterLiveDataService {

    List<WaterLiveData> getAllWaterLiveData();
    WaterLiveData getWaterLiveDataById(Integer live_data_id);
}
