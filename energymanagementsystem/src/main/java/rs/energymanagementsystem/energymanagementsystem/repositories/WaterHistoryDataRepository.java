package rs.energymanagementsystem.energymanagementsystem.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rs.energymanagementsystem.energymanagementsystem.entities.WaterHistoryData;

@Repository
public interface WaterHistoryDataRepository extends CrudRepository<WaterHistoryData, Integer> {

}
