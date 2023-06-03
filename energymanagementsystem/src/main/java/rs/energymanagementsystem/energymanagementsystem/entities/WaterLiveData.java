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

    public Integer getLive_data_id() {
        return live_data_id;
    }

    public void setLive_data_id(Integer live_data_id) {
        this.live_data_id = live_data_id;
    }

    public Integer getConsumption() {
        return consumption;
    }

    public void setConsumption(Integer consumption) {
        this.consumption = consumption;
    }

    public String getFault_detected() {
        return fault_detected;
    }

    public void setFault_detected(String fault_detected) {
        this.fault_detected = fault_detected;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

}
