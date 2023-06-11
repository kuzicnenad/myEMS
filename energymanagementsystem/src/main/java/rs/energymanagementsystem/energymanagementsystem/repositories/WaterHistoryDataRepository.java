package rs.energymanagementsystem.energymanagementsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.energymanagementsystem.energymanagementsystem.entities.WaterHistoryData;



public interface WaterHistoryDataRepository extends JpaRepository<WaterHistoryData, Integer> {

}
