package rs.energymanagementsystem.energymanagementsystem.entities;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data /* Getters and Setters */
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "water_live_data") // Tells Hibernate to make a table out of this class
public class WaterLiveData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer liveDataId;

    @Column
    private Integer consumption;

    @Column
    private String faultDetected;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column
    private Date endTime;

}
