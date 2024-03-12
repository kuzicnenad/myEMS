package rs.energymanagementsystem.energymanagementsystem.entities;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data /* Getters and Setters */
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "gasLiveData") // Tells Hibernate to make a table out of this class
public class GasLiveData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "liveDataId")
    private Integer liveDataId;

    @Column(name = "consumption")
    private Integer consumption;

    @Column(name = "faultDetected")
    private String faultDetected;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "startTime")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "endTime")
    private Date endTime;




}