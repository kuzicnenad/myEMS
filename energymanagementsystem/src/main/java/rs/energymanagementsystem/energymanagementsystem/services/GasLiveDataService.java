package rs.energymanagementsystem.energymanagementsystem.services;

import org.springframework.stereotype.Repository;
import rs.energymanagementsystem.energymanagementsystem.entities.GasLiveData;

import java.util.List;

@Repository
public interface GasLiveDataService {
    List<GasLiveData> getAllGasLiveData();
    GasLiveData getGasLiveDataById(Integer liveDataId);
    List<GasLiveData> getLastData();
}
