package rs.energymanagementsystem.energymanagementsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.energymanagementsystem.energymanagementsystem.entities.WaterHistoryData;

@Repository
public interface WaterHistoryDataRepository extends JpaRepository<WaterHistoryData, Integer> {

}
