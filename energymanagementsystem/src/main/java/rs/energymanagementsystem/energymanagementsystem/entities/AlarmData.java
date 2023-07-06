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
@Table(name = "alarm_data")
public class AlarmData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer alarm_id;

    @Column
    private Integer alarm_code;

    @Column
    private String alarm_desc;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date time_stamp;

    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @UpdateTimestamp
    private Date acknowledged_time;

    @Column(name = "ack_flag")
    private Boolean ack_flag;

}
