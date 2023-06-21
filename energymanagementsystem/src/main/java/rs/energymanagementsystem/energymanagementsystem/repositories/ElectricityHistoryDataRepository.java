package rs.energymanagementsystem.energymanagementsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import rs.energymanagementsystem.energymanagementsystem.entities.ElectricityHistoryData;

import java.util.List;


public interface ElectricityHistoryDataRepository extends JpaRepository<ElectricityHistoryData, Integer> {
    @Query(value = "select * from electricity_history_data ORDER BY hist_data_id DESC",
            nativeQuery = true)
    public List<ElectricityHistoryData> getHistoryData();
}
