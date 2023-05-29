package rs.energymanagementsystem.energymanagementsystem.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rs.energymanagementsystem.energymanagementsystem.entities.Passwords;

@Repository
public interface PasswordsRepository extends CrudRepository<Passwords, Integer> {

}
