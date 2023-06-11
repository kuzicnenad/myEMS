package rs.energymanagementsystem.energymanagementsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.energymanagementsystem.energymanagementsystem.entities.GasHistoryData;



public interface GasHistoryDataRepository extends JpaRepository<GasHistoryData, Integer> {

}
