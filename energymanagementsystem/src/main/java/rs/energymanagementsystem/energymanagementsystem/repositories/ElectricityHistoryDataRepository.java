package rs.energymanagementsystem.energymanagementsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import rs.energymanagementsystem.energymanagementsystem.entities.Devices;
import rs.energymanagementsystem.energymanagementsystem.entities.ElectricityHistoryData;

import java.awt.print.Pageable;
import java.util.List;

public interface ElectricityHistoryDataRepository extends JpaRepository<ElectricityHistoryData, Integer>, PagingAndSortingRepository<ElectricityHistoryData, Integer> {

}
