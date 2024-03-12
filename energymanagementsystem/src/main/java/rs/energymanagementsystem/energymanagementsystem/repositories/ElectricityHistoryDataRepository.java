package rs.energymanagementsystem.energymanagementsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import rs.energymanagementsystem.energymanagementsystem.entities.ElectricityHistoryData;

import java.util.Date;

public interface ElectricityHistoryDataRepository extends JpaRepository<ElectricityHistoryData, Integer>, PagingAndSortingRepository<ElectricityHistoryData, Integer> {

    @Query(value = "SELECT MAX(electricityConsumption) FROM electricity_history_data",
            nativeQuery = true)
    Integer getMaxValue();

    @Query(value =  "SELECT date FROM electricity_history_data WHERE electricityConsumption = " +
            "(SELECT MAX(electricityConsumption) FROM electricity_history_data)",
            nativeQuery = true)
    Date getMaxValueDate();

    @Query(value = "SELECT AVG(electricityConsumption) FROM electricity_history_data",
            nativeQuery = true)
    Integer getAvgValue();

    @Query(value = "SELECT date FROM electricity_history_data ORDER BY histDataId ASC LIMIT 1",
          nativeQuery = true)
    Date getFirstDate();

    @Query(value = "SELECT date FROM electricity_history_data ORDER BY histDataId DESC LIMIT 1",
            nativeQuery = true)
    Date getLastDate();

    @Query(value = "SELECT MIN(electricityConsumption) FROM electricity_history_data",
            nativeQuery = true)
    Integer getMinValue();

    @Query(value =  "SELECT date FROM electricity_history_data WHERE electricityConsumption = " +
                    "(SELECT MIN(electricityConsumption) FROM electricity_history_data)",
            nativeQuery = true)
    Date getMinValueDate();

}
