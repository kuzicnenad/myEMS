package rs.energymanagementsystem.energymanagementsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import rs.energymanagementsystem.energymanagementsystem.entities.WaterHistoryData;

import java.util.Date;


public interface WaterHistoryDataRepository extends JpaRepository<WaterHistoryData, Integer> {

    @Query(value = "SELECT MAX(waterConsumption) FROM water_history_data",
            nativeQuery = true)
    Integer getMaxValue();

    @Query(value =  "SELECT date FROM water_history_data WHERE waterConsumption = " +
            "(SELECT MAX(waterConsumption) FROM water_history_data)",
            nativeQuery = true)
    Date getMaxValueDate();

    @Query(value = "SELECT AVG(waterConsumption) FROM water_history_data",
            nativeQuery = true)
    Integer getAvgValue();

    @Query(value = "SELECT date FROM water_history_data ORDER BY histDataId ASC LIMIT 1",
            nativeQuery = true)
    Date getFirstDate();

    @Query(value = "SELECT date FROM water_history_data ORDER BY histDataId DESC LIMIT 1",
            nativeQuery = true)
    Date getLastDate();

    @Query(value = "SELECT MIN(waterConsumption) FROM water_history_data",
            nativeQuery = true)
    Integer getMinValue();

    @Query(value =  "SELECT date FROM water_history_data WHERE waterConsumption = " +
            "(SELECT MIN(waterConsumption) FROM water_history_data)",
            nativeQuery = true)
    Date getMinValueDate();
}
