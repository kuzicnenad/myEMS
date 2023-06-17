package rs.energymanagementsystem.energymanagementsystem.services;

import org.springframework.stereotype.Repository;
import rs.energymanagementsystem.energymanagementsystem.entities.WaterLiveData;

import java.util.List;

@Repository
public interface WaterLiveDataService {

    List<WaterLiveData> getAllWaterLiveData();
    WaterLiveData getWaterLiveDataById(Integer live_data_id);
    List<WaterLiveData> getLastData();
}
