package rs.energymanagementsystem.energymanagementsystem.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rs.energymanagementsystem.energymanagementsystem.entities.GasHistoryData;

@Repository
public interface GasHistoryDataRepository extends CrudRepository<GasHistoryData, Integer> {

}
