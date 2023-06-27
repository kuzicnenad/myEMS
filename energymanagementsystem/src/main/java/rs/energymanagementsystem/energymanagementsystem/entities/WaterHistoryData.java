package rs.energymanagementsystem.energymanagementsystem.entities;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data /* Getters and Setters */
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "water_history_data")
public class WaterHistoryData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer hist_data_id;

    @Column
    private Integer water_consumption;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column
    private Date date;


}
