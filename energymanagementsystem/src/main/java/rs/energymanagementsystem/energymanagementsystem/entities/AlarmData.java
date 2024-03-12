package rs.energymanagementsystem.energymanagementsystem.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data /* Getters and Setters */
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "alarmData")
public class AlarmData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column 
    private Integer alarmId;

    @Column
    private Integer alarmCode;

    @Column
    private String alarmDesc;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date timeStamp;

    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @UpdateTimestamp
    private Date acknowledgedTime;

    @Column(name = "ackFlag")
    private Boolean ackFlag;

}
