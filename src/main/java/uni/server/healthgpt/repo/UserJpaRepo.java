package uni.server.healthgpt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.server.healthgpt.data.entity.User;

import java.util.Optional;

public interface UserJpaRepo extends JpaRepository<User , Long> {
    Optional<User> findByUid(String email);
}
