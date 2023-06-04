package rs.energymanagementsystem.energymanagementsystem.entities;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

@Data /* Getters and Setters */
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "passwords") // Tells Hibernate to make a table out of this class
public class Passwords {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer hash_algorithm_id;

    @Column
    private Integer user_id;

    @Column
    private String password_hash;

    @DateTimeFormat
    @Column
    private String time_stamp;


}
