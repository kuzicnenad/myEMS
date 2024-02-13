package rs.energymanagementsystem.energymanagementsystem.entities;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Data
@Entity
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"}),
        @UniqueConstraint(columnNames = {"email"})
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String username;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @CreationTimestamp
    private Date creation_date;
    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @UpdateTimestamp
    private Date last_update;
    @Column(name = "active_flag")
    private boolean active_flag;

    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();
    /** creates an object that is a HashSet instance, and assigns a reference to that object to a variable whose type is Set
     * Hibernate doesn't remove entities from a List in an efficient way **/
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public Date getLast_update() {
        return last_update;
    }

    public boolean isActive_flag() {
        return active_flag;
    }

    public Set<Role> getRoles() {
        return roles;
    }
}
