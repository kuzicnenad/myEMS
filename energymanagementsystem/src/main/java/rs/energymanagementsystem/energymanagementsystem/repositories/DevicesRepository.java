package rs.energymanagementsystem.energymanagementsystem.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rs.energymanagementsystem.energymanagementsystem.entities.Devices;

import java.util.List;

public interface DevicesRepository extends JpaRepository<Devices, Integer> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE devices d SET d.active_flag = IF(active_flag=1, 0, 1) WHERE d.device_id = :device_id",
            nativeQuery = true)
    void deviceActiveFlag(@Param("device_id") Integer device_id);


}


