package rs.energymanagementsystem.energymanagementsystem.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rs.energymanagementsystem.energymanagementsystem.entities.WaterLiveData;

@Repository
public interface WaterLiveDataRepository extends CrudRepository<WaterLiveData, Integer> {

}
