package rs.energymanagementsystem.energymanagementsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.energymanagementsystem.energymanagementsystem.entities.ElectricityLiveData;



public interface ElectricityLiveDataRepository extends JpaRepository<ElectricityLiveData, Integer> {

}
