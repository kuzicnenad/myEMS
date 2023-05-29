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

    public Integer getHash_algorithm_id() {
        return hash_algorithm_id;
    }

    public void setHash_algorithm_id(Integer hash_algorithm_id) {
        this.hash_algorithm_id = hash_algorithm_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public String getTime_stamp() {
        return time_stamp;
    }

    public void setTimestamp(String time_stamp) {
        this.time_stamp = time_stamp;
    }
}
