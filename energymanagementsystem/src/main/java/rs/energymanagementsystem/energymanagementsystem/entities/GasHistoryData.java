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
@Table(name = "gas_history_data") // Tells Hibernate to make a table out of this class
public class GasHistoryData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer hist_data_id;

    @Column
    private Integer gas_consumption;

    @DateTimeFormat
    @Column
    private String date;

    public Integer getHist_data_id() {
        return hist_data_id;
    }

    public void setHist_data_id(Integer hist_data_id) {
        this.hist_data_id = hist_data_id;
    }

    public Integer getGas_consumption() {
        return gas_consumption;
    }

    public void setGas_consumption(Integer gas_consumption) {
        this.gas_consumption = gas_consumption;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
