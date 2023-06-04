package rs.energymanagementsystem.energymanagementsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.energymanagementsystem.energymanagementsystem.entities.GasLiveData;

@Repository
public interface GasLiveDataRepository extends JpaRepository<GasLiveData, Integer> {

}
