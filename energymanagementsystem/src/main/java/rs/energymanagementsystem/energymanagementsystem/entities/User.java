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
    private Date creationDate;
    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @UpdateTimestamp
    private Date lastUpdate;
    @Column(name = "activeFlag")
    private boolean activeFlag;

    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(
            name = "usersRoles",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "roleId")
    )
    private Set<Role> roles = new HashSet<>();
    /** creates an object that is a HashSet instance, and assigns a reference to that object to a variable whose type is Set
     * Hibernate doesn't remove entities from a List in an efficient way **/
    @Column
    public Long getId() {
        return id;
    }
    @Column
    public String getName() {
        return name;
    }
    @Column
    public String getUsername() {
        return username;
    }
    @Column
    public String getEmail() {
        return email;
    }
    @Column
    public String getPassword() {
        return password;
    }
    @Column
    public Date getCreationDate() {
        return creationDate;
    }
    @Column
    public Date getLastUpdate() {
        return lastUpdate;
    }
    @Column
    public boolean isActiveFlag() {
        return activeFlag;
    }
    @Column
    public Set<Role> getRoles() {
        return roles;
    }
}
