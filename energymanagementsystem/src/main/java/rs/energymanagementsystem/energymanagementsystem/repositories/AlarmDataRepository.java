package rs.energymanagementsystem.energymanagementsystem.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rs.energymanagementsystem.energymanagementsystem.entities.AlarmData;

import java.util.List;

/* Table, PK Data type */
public interface AlarmDataRepository extends JpaRepository<AlarmData, Integer> {
    @Query(value = "select * FROM alarm_data ORDER BY alarm_id DESC LIMIT 10", nativeQuery = true)
    public List<AlarmData> getLatestData();

    /** ---------------------------------------------------------------------------------------
     * Query to change acknowledge alarm, 0 -> Inactive, 1 -> Active
     --------------------------------------------------------------------------------------- **/
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE alarm_data a SET a.ack_flag = 1, a.acknowledged_time = CURRENT_TIMESTAMP WHERE a.alarm_id = :alarm_id",
            nativeQuery = true)
    void alarmAckFlag(@Param("alarm_id") Integer alarm_id);

    @Query(value = "SELECT a.ack_flag FROM alarm_data a where a.alarm_id = :alarm_id", nativeQuery = true)
    Boolean ackValueCheck(@Param("alarm_id") Integer alarm_id);

}
