package rs.energymanagementsystem.energymanagementsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import rs.energymanagementsystem.energymanagementsystem.entities.GasLiveData;

import java.util.List;


public interface GasLiveDataRepository extends JpaRepository<GasLiveData, Integer> {


    /** ---------------------------------------------------------------------------------------
     * - Get last 10 records (for live data page)
     --------------------------------------------------------------------------------------- **/
    @Query(value = "select * FROM gasLiveData ORDER BY liveDataId DESC LIMIT 10", nativeQuery = true)
    public List<GasLiveData> getLastData();
}
