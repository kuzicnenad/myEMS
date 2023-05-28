package rs.energymanagementsystem.energymanagementsystem.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "alarm_data")
public class AlarmData {

    @Id
    @GeneratedValue
    @Column
    private Integer alarm_id;

    @Column
    private Integer alarm_code;

    @Column
    private String slarm_desc;

    @Column
    private String time_stamp;


}
