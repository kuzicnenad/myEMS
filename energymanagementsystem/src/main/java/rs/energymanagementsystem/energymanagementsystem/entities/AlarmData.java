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
    private String alarm_desc;

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

    public String getAlarm_desc() {
        return alarm_desc;
    }

    public void setAlarm_desc(String alarm_desc) {
        this.alarm_desc = alarm_desc;
    }

    public String getTime_stamp() {
        return time_stamp;
    }

    public void setTime_stamp(String time_stamp) {
        this.time_stamp = time_stamp;
    }
}
