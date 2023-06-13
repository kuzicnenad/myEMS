package rs.energymanagementsystem.energymanagementsystem.services;

import rs.energymanagementsystem.energymanagementsystem.entities.ElectricityLiveData;

import java.util.List;

public interface ElectricityLiveDataService {
    List<ElectricityLiveData> getAllElectricityLiveData();
    ElectricityLiveData getElectricityLiveDataById(Integer live_data_id);
}
