package rs.energymanagementsystem.energymanagementsystem.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data /* Getters and Setters */
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "electricity_live_data") // Tells Hibernate to make a table out of this class
public class ElectricityLiveData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer live_data_id;

    @Column
    private Integer consumption;

    @Column
    private String fault_detected;

    @DateTimeFormat
    @Column
    Date start_time;

    @DateTimeFormat
    @Column
    Date end_time;

}
