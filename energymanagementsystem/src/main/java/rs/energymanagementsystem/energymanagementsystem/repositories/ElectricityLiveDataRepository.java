package rs.energymanagementsystem.energymanagementsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import rs.energymanagementsystem.energymanagementsystem.entities.ElectricityLiveData;

import java.util.List;


public interface ElectricityLiveDataRepository extends JpaRepository<ElectricityLiveData, Integer> {
    @Query(value = "select * from electricity_live_data ORDER BY live_data_id DESC LIMIT 5",
            nativeQuery = true)
    public List<ElectricityLiveData> getLastData();

}
