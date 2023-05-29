package rs.energymanagementsystem.energymanagementsystem.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rs.energymanagementsystem.energymanagementsystem.entities.AlarmData;

@Repository
public interface ElectricityLiveDataRepository extends CrudRepository<AlarmData, Integer> {

}
