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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deviceId")
    private Integer deviceId;

    @Column(name = "deviceName")
    private String deviceName;

    @Column(name = "description")
    private String description;

    @Column(name = "productionDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date productionDate;

    @Column(name = "madeIn")
    private String madeIn;

    @Column(name = "lastUpdate")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @UpdateTimestamp
    private Date lastUpdate;

    @Column(name = "activeFlag")
    private Boolean activeFlag;

}