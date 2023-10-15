package uni.server.healthgpt.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.server.healthgpt.data.entity.MailPass;

public interface MailPassRepo extends JpaRepository<MailPass,String> {
}
