package rs.energymanagementsystem.energymanagementsystem.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "water_live_data") // Tells Hibernate to make a table out of this class
public class WaterLiveData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private  Integer live_data_id;

    @Column
    private float consumption;

    @Column
    private String fault_detected;

    @DateTimeFormat
    @Column
    private String start_time;

    @DateTimeFormat
    @Column
    private String end_time;

    @Column
    private Character handshake;
}
