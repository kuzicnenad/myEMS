package rs.energymanagementsystem.energymanagementsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import rs.energymanagementsystem.energymanagementsystem.entities.WaterHistoryData;

import java.util.Date;


public interface WaterHistoryDataRepository extends JpaRepository<WaterHistoryData, Integer> {

    @Query(value = "SELECT MAX(water_consumption) FROM water_history_data",
            nativeQuery = true)
    Integer getMaxValue();

    @Query(value =  "SELECT date FROM water_history_data WHERE water_consumption = " +
            "(SELECT MAX(water_consumption) FROM water_history_data)",
            nativeQuery = true)
    Date getMaxValueDate();
}
