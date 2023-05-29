package rs.energymanagementsystem.energymanagementsystem.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rs.energymanagementsystem.energymanagementsystem.entities.ElectricityHistoryData;

@Repository
public interface ElectricityHistoryDataRepository extends CrudRepository<ElectricityHistoryData, Integer> {

}
