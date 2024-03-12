package rs.energymanagementsystem.energymanagementsystem.services;

import org.springframework.stereotype.Repository;
import rs.energymanagementsystem.energymanagementsystem.entities.ElectricityLiveData;

import java.util.List;

@Repository
public interface ElectricityLiveDataService {
    List<ElectricityLiveData> getAllElectricityLiveData();
    ElectricityLiveData getElectricityLiveDataById(Integer liveDataId);
    List<ElectricityLiveData> getLastData();

}
