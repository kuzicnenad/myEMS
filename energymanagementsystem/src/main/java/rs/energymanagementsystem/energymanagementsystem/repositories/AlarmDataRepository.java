package rs.energymanagementsystem.energymanagementsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.energymanagementsystem.energymanagementsystem.entities.AlarmData;

                                                        /* Table, PK Data type */
public interface AlarmDataRepository extends JpaRepository<AlarmData, Integer> {

}
