package rs.energymanagementsystem.energymanagementsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rs.energymanagementsystem.energymanagementsystem.entities.Users;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    @Query(value = "SELECT * FROM users ORDER BY user_id DESC LIMIT 3", nativeQuery = true)
    public List<Users> getLastUsers();
}
