package uni.server.healthgpt.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uni.server.healthgpt.data.entity.User;
import uni.server.healthgpt.repo.UserJpaRepo;
import uni.server.healthgpt.service.impl.ResponseService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final ResponseService responseService; // 결과를 처리하는 Service
    private final UserJpaRepo userJpaRepo; // Jpa를 활용한 CRUD 쿼리 가능

    public Optional<User> getUserById(String msrl){
        return userJpaRepo.findByUid(msrl);
    }
}
