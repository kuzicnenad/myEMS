package rs.energymanagementsystem.energymanagementsystem.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rs.energymanagementsystem.energymanagementsystem.entities.ElectricityLiveData;

@Repository
public interface ElectricityLiveDataRepository extends CrudRepository<ElectricityLiveData, Integer> {

}
