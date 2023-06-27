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
@Table(name = "devices")
public class Devices {

    @Id
    @GeneratedValue
    @Column
    private Integer device_id;

    @Column
    private String device_name;

    @Column
    private String description;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date production_date;

    @Column
    private String made_in;

    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @UpdateTimestamp
    private Date last_update;

    @Column
    private Boolean active_flag;

}
