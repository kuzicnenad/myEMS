package rs.energymanagementsystem.energymanagementsystem.services;

import org.springframework.stereotype.Repository;
import rs.energymanagementsystem.energymanagementsystem.entities.ElectricityLiveData;

import java.util.List;

@Repository
public interface ElectricityLiveDataService {
    List<ElectricityLiveData> getAllElectricityLiveData();
    ElectricityLiveData getElectricityLiveDataById(Integer live_data_id);
    List<ElectricityLiveData> getLastData();

}
