package rs.energymanagementsystem.energymanagementsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import rs.energymanagementsystem.energymanagementsystem.entities.GasLiveData;

import java.util.List;


public interface GasLiveDataRepository extends JpaRepository<GasLiveData, Integer> {
    @Query(value = "select * FROM gas_live_data ORDER BY live_data_id DESC LIMIT 10", nativeQuery = true)
    public List<GasLiveData> getLastData();
}
