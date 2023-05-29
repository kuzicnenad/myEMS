package rs.energymanagementsystem.energymanagementsystem.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rs.energymanagementsystem.energymanagementsystem.entities.GasLiveData;

@Repository
public interface GasLiveDataRepository extends CrudRepository<GasLiveData, Integer> {

}
