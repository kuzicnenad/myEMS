package rs.energymanagementsystem.energymanagementsystem.services;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;
import rs.energymanagementsystem.energymanagementsystem.entities.ElectricityHistoryData;

import java.util.List;

@Repository
public interface ElectricityHistoryDataService {
    List<ElectricityHistoryData> getAllElectricityHistoryData();
    ElectricityHistoryData getElectricityHistoryDataById(Integer hist_data_id);
    Page<ElectricityHistoryData> getHistoryDataElectricity(int pageNo, int pageSize);

    Integer getElectricityPeakValue();
}
