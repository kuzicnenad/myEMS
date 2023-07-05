package rs.energymanagementsystem.energymanagementsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import rs.energymanagementsystem.energymanagementsystem.entities.ElectricityHistoryData;

public interface ElectricityHistoryDataRepository extends JpaRepository<ElectricityHistoryData, Integer>, PagingAndSortingRepository<ElectricityHistoryData, Integer> {

    @Query(value = "SELECT MAX(electricity_consumption) FROM electricity_history_data",
            nativeQuery = true)
    Integer getMaxValue();

    @Query(value = "SELECT AVG(electricity_consumption) FROM electricity_history_data",
            nativeQuery = true)
    Float getAvgValue();

    @Query(value = "SELECT MIN(electricity_consumption) FROM electricity_history_data",
            nativeQuery = true)
    Integer getMinValue();

}
