package rs.energymanagementsystem.energymanagementsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import rs.energymanagementsystem.energymanagementsystem.entities.ElectricityHistoryData;

import java.util.Date;

public interface ElectricityHistoryDataRepository extends JpaRepository<ElectricityHistoryData, Integer>, PagingAndSortingRepository<ElectricityHistoryData, Integer> {

    @Query(value = "SELECT MAX(electricityConsumption) FROM electricityHistoryData",
            nativeQuery = true)
    Integer getMaxValue();

    @Query(value =  "SELECT date FROM electricityHistoryData WHERE electricityConsumption = " +
            "(SELECT MAX(electricityConsumption) FROM electricityHistoryData)",
            nativeQuery = true)
    Date getMaxValueDate();

    @Query(value = "SELECT AVG(electricityConsumption) FROM electricityHistoryData",
            nativeQuery = true)
    Integer getAvgValue();

    @Query(value = "SELECT date FROM electricityHistoryData ORDER BY histDataId ASC LIMIT 1",
          nativeQuery = true)
    Date getFirstDate();

    @Query(value = "SELECT date FROM electricityHistoryData ORDER BY histDataId DESC LIMIT 1",
            nativeQuery = true)
    Date getLastDate();

    @Query(value = "SELECT MIN(electricityConsumption) FROM electricityHistoryData",
            nativeQuery = true)
    Integer getMinValue();

    @Query(value =  "SELECT date FROM electricityHistoryData WHERE electricityConsumption = " +
                    "(SELECT MIN(electricityConsumption) FROM electricityHistoryData)",
            nativeQuery = true)
    Date getMinValueDate();

}
