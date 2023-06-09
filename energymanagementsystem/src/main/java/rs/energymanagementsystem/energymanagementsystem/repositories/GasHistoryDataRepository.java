package rs.energymanagementsystem.energymanagementsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import rs.energymanagementsystem.energymanagementsystem.entities.GasHistoryData;

import java.util.Date;


public interface GasHistoryDataRepository extends JpaRepository<GasHistoryData, Integer> {

    @Query(value = "SELECT MAX(gas_consumption) FROM gas_history_data",
            nativeQuery = true)
    Integer getMaxValue();

    @Query(value =  "SELECT date FROM gas_history_data WHERE gas_consumption = " +
            "(SELECT MAX(gas_consumption) FROM gas_history_data)",
            nativeQuery = true)
    Date getMaxValueDate();

    @Query(value = "SELECT AVG(gas_consumption) FROM gas_history_data",
            nativeQuery = true)
    Integer getAvgValue();

    @Query(value = "SELECT date FROM gas_history_data ORDER BY hist_data_id ASC LIMIT 1",
            nativeQuery = true)
    Date getFirstDate();

    @Query(value = "SELECT date FROM gas_history_data ORDER BY hist_data_id DESC LIMIT 1",
            nativeQuery = true)
    Date getLastDate();

    @Query(value = "SELECT MIN(gas_consumption) FROM gas_history_data",
            nativeQuery = true)
    Integer getMinValue();

    @Query(value =  "SELECT date FROM gas_history_data WHERE gas_consumption = " +
            "(SELECT MIN(gas_consumption) FROM gas_history_data)",
            nativeQuery = true)
    Date getMinValueDate();

}
