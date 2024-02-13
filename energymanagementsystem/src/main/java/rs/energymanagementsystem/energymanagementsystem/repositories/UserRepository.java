package rs.energymanagementsystem.energymanagementsystem.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rs.energymanagementsystem.energymanagementsystem.entities.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /** ---------------------------------------------------------------------------------------
     * Query to change device active flag, 0 -> Inactive, 1 -> Active
     --------------------------------------------------------------------------------------- **/
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE user u SET u.active_flag = IF(active_flag=1, 0, 1), u.last_update = CURRENT_TIMESTAMP WHERE u.id = :id",
            nativeQuery = true)
    void userActiveFlag(@Param("id") Long id);

    /** List for easier sorting **/
    @Query(value = "SELECT * FROM user ORDER BY name ASC", nativeQuery = true)
    public List<User> getUsersList();

    Optional<User> findByEmail(String email);
    Optional<User> findByUsernameOrEmail(String username, String email);
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    @Query(value = "SELECT active_flag FROM user", nativeQuery = true)
    boolean active_flag();

}
