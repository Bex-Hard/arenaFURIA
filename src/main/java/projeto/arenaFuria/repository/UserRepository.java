package projeto.arenaFuria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import projeto.arenaFuria.entity.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<UserDetails> findByEmail(String email);

    User findUserById(String id);
}
