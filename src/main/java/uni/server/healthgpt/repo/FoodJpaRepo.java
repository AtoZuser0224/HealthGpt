package uni.server.healthgpt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.server.healthgpt.data.entity.Food;

public interface FoodJpaRepo extends JpaRepository<Food , Long> {
    Food findByName(String name);
}
