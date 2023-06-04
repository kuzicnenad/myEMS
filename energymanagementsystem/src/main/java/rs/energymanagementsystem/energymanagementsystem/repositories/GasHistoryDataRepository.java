package rs.energymanagementsystem.energymanagementsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.energymanagementsystem.energymanagementsystem.entities.GasHistoryData;

@Repository
public interface GasHistoryDataRepository extends JpaRepository<GasHistoryData, Integer> {

}
