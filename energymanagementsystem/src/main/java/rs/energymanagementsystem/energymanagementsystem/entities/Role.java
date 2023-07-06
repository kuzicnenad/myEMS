package rs.energymanagementsystem.energymanagementsystem.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

@Data /* Getters and Setters */
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
