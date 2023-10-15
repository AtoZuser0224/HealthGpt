package uni.server.healthgpt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.server.healthgpt.data.entity.EtcFood;

public interface EtcJpaRepo extends JpaRepository<EtcFood , Long> {
}
