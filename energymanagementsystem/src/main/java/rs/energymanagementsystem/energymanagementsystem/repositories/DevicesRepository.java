package rs.energymanagementsystem.energymanagementsystem.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rs.energymanagementsystem.energymanagementsystem.entities.Devices;

import java.util.List;

public interface DevicesRepository extends JpaRepository<Devices, Integer> {

    /** ---------------------------------------------------------------------------------------
     * Query to change device active flag, 0 -> Inactive, 1 -> Active
     --------------------------------------------------------------------------------------- **/
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE devices d SET d.activeFlag = IF(activeFlag=1, 0, 1), d.lastUpdate = CURRENT_TIMESTAMP WHERE d.deviceId = :deviceId",
            nativeQuery = true)
    void deviceActiveFlag(@Param("deviceId") Integer deviceId);

    /** ---------------------------------------------------------------------------------------
     * Get active devices list
     --------------------------------------------------------------------------------------- **/
    @Query(value = "SELECT * FROM devices WHERE activeFlag = 1",
            nativeQuery = true)
    public List<Devices> getActiveDevices();


    /** ---------------------------------------------------------------------------------------
     * Get number of Active devices
     --------------------------------------------------------------------------------------- **/
    @Query(value = "SELECT COUNT(deviceId) FROM devices WHERE activeFlag = 1;",
            nativeQuery = true)
    public Integer numberOfActiveDevices();

    /** ---------------------------------------------------------------------------------------
     * Get number of Inactive devices
     --------------------------------------------------------------------------------------- **/
    @Query(value = "SELECT COUNT(deviceId) FROM devices WHERE activeFlag = 0;",
            nativeQuery = true)
    public Integer numberOfInactiveDevices();
}



