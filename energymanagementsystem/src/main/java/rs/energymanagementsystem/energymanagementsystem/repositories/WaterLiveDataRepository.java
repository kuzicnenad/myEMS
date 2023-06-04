package rs.energymanagementsystem.energymanagementsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.energymanagementsystem.energymanagementsystem.entities.WaterLiveData;

@Repository
public interface WaterLiveDataRepository extends JpaRepository<WaterLiveData, Integer> {

}
