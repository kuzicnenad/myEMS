package rs.energymanagementsystem.energymanagementsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.energymanagementsystem.energymanagementsystem.entities.ElectricityHistoryData;

@Repository
public interface ElectricityHistoryDataRepository extends JpaRepository<ElectricityHistoryData, Integer> {

}
