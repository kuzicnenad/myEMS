package rs.energymanagementsystem.energymanagementsystem.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Data /* Getters and Setters */
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "electricity_history_data") // Tells Hibernate to make a table out of this class
public class ElectricityHistoryData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer hist_data_id;

    @Column
    private Integer electricity_consumption;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column
    private Date date;


}
