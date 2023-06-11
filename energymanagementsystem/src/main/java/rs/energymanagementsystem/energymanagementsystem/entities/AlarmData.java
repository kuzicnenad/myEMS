package rs.energymanagementsystem.energymanagementsystem.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String time_stamp;

    public Integer getAlarm_id() {
        return alarm_id;
    }

}
