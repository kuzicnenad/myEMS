package rs.energymanagementsystem.energymanagementsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.energymanagementsystem.energymanagementsystem.entities.AlarmData;

@Repository                                             /* Table, PK Data type */
public interface AlarmDataRepository extends JpaRepository<AlarmData, Integer> {

}
