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
    @Query(value = "select * FROM alarmData ORDER BY alarmId DESC LIMIT 10", nativeQuery = true)
    public List<AlarmData> getLatestData();

    /** ---------------------------------------------------------------------------------------
     * Query to change acknowledge alarm, 0 -> Inactive, 1 -> Active
     --------------------------------------------------------------------------------------- **/
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE alarmData a SET a.ackFlag = 1, a.acknowledgedTime = CURRENT_TIMESTAMP WHERE a.alarmId = :alarmId",
            nativeQuery = true)
    void alarmAckFlag(@Param("alarmId") Integer alarmId);

    @Query(value = "SELECT a.ackFlag FROM alarmData a where a.alarmId = :alarmId", nativeQuery = true)
    Boolean ackValueCheck(@Param("alarmId") Integer alarmId);

}
