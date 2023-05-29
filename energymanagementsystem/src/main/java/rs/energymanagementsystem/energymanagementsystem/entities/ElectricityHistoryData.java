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
@Table(name = "electricity_history_data") // Tells Hibernate to make a table out of this class
public class ElectricityHistoryData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private  Integer hist_data_id;

    @Column
    private float electricity_consumption;

    @DateTimeFormat
    @Column
    private String date;

    public Integer getHist_data_id() {
        return hist_data_id;
    }

    public void setHist_data_id(Integer hist_data_id) {
        this.hist_data_id = hist_data_id;
    }

    public float getElectricity_consumption() {
        return electricity_consumption;
    }

    public void setElectricity_consumption(float electricity_consumption) {
        this.electricity_consumption = electricity_consumption;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
