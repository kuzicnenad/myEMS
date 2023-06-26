package rs.energymanagementsystem.energymanagementsystem.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    private Date production_date;

    @Column
    private String made_in;

    @Column
    private Date last_update;


}
