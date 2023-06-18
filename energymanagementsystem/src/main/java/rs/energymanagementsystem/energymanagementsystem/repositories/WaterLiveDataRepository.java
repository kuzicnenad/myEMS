package rs.energymanagementsystem.energymanagementsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import rs.energymanagementsystem.energymanagementsystem.entities.WaterLiveData;

import java.util.List;


public interface WaterLiveDataRepository extends JpaRepository<WaterLiveData, Integer> {
    @Query(value = "select * FROM water_live_data ORDER BY live_data_id DESC LIMIT 10", nativeQuery = true)
    public List<WaterLiveData> getLastData();
}
