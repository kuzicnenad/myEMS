package rs.energymanagementsystem.energymanagementsystem.entities;


import jakarta.persistence.*;
import lombok.*;

@Data /* Getters and Setters */
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users") // Tells Hibernate to make a table out of this class
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer user_id;

    @Column
    private String user_login;

    @Column
    private String first_name;

    @Column
    private String last_name;

    @Column
    private Integer job_title;

    @Column
    private String time_stamp;



}
