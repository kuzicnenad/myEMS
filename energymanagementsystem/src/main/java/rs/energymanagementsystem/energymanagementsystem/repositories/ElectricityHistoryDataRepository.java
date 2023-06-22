package rs.energymanagementsystem.energymanagementsystem.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import rs.energymanagementsystem.energymanagementsystem.entities.ElectricityHistoryData;
import java.awt.print.Pageable;

public interface ElectricityHistoryDataRepository extends JpaRepository<ElectricityHistoryData, Integer> {


}
