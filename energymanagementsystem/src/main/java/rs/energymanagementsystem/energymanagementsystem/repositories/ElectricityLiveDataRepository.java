package rs.energymanagementsystem.energymanagementsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.energymanagementsystem.energymanagementsystem.entities.ElectricityLiveData;

@Repository
public interface ElectricityLiveDataRepository extends JpaRepository<ElectricityLiveData, Integer> {

}
