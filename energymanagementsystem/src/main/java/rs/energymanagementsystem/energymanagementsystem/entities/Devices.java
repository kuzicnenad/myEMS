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

    @Column(name = "active_flag", columnDefinition = "varchar(20) default '0'")
    private Boolean active_flag;

}


/** NASTAVI SA LINKOM SA STACKOVERFLOW
 * TREBA DA ISPRAVIM ZASTO MI ACTIVE_FLAG NIJE DEFAULT PREKO THYMELEAFE
 * KAKO RAZUMEM, PROBLEM JE THYMELEAFE, SA NATIVE QUERY NA REPO BI RADILO
 * DA POGLEDAM DA LI MOGU DA ZAMENIM THYMELEAFE BAR ZA NESTO NA NATIVE QUERY
 * **/