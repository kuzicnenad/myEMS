package rs.energymanagementsystem.energymanagementsystem.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rs.energymanagementsystem.energymanagementsystem.entities.AlarmData;

@Repository
public interface GasLiveDataRepository extends CrudRepository<AlarmData, Integer> {

}
