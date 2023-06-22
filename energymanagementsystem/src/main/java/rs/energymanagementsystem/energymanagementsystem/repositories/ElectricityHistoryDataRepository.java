package rs.energymanagementsystem.energymanagementsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.energymanagementsystem.energymanagementsystem.entities.ElectricityHistoryData;

public interface ElectricityHistoryDataRepository extends JpaRepository<ElectricityHistoryData, Integer> {


}
