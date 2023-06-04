package rs.energymanagementsystem.energymanagementsystem.entities;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

@Data /* Getters and Setters */
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "gas_live_data") // Tells Hibernate to make a table out of this class
public class GasLiveData {

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
    private String start_time;

    @DateTimeFormat
    @Column
    private String end_time;




}