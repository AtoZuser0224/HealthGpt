package uni.server.healthgpt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.server.healthgpt.data.entity.Today;
import uni.server.healthgpt.data.entity.User;

public interface TodayJpaRepo extends JpaRepository<Today , Long> {
    Today findByDayAndUser(String day, User user);
}
