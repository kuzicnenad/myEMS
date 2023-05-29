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

    public Integer getAlarm_id() {
        return alarm_id;
    }

    public void setAlarm_id(Integer alarm_id) {
        this.alarm_id = alarm_id;
    }

    public Integer getAlarm_code() {
        return alarm_code;
    }

    public void setAlarm_code(Integer alarm_code) {
        this.alarm_code = alarm_code;
    }

    public String getSlarm_desc() {
        return slarm_desc;
    }

    public void setSlarm_desc(String slarm_desc) {
        this.slarm_desc = slarm_desc;
    }

    public String getTime_stamp() {
        return time_stamp;
    }

    public void setTime_stamp(String time_stamp) {
        this.time_stamp = time_stamp;
    }
}
