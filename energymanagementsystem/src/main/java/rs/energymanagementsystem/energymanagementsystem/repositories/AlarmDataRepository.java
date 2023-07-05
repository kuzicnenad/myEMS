package rs.energymanagementsystem.energymanagementsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import rs.energymanagementsystem.energymanagementsystem.entities.AlarmData;
import rs.energymanagementsystem.energymanagementsystem.entities.WaterLiveData;

import java.util.List;

/* Table, PK Data type */
public interface AlarmDataRepository extends JpaRepository<AlarmData, Integer> {
    @Query(value = "select * FROM alarm_data ORDER BY alarm_id DESC LIMIT 10", nativeQuery = true)
    public List<AlarmData> getLatestData();
}
