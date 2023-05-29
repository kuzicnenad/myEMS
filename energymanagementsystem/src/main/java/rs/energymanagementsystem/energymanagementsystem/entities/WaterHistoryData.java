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
@Table(name = "water_history_data")
public class WaterHistoryData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer hist_data_id;

    @Column
    private Integer water_consumption;

    @DateTimeFormat
    @Column
    private String date;

    public Integer getHist_data_id() {
        return hist_data_id;
    }

    public void setHist_data_id(Integer hist_data_id) {
        this.hist_data_id = hist_data_id;
    }

    public Integer getWater_consumption() {
        return water_consumption;
    }

    public void setWater_consumption(Integer water_consumption) {
        this.water_consumption = water_consumption;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
