package uni.server.healthgpt.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import uni.server.healthgpt.advice.exception.UserNotFoundExceptionCustom;
import uni.server.healthgpt.repo.UserJpaRepo;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserJpaRepo userJpaRepo;

    public UserDetails loadUserByUsername(String userPk) {
        return userJpaRepo.findById(Long.valueOf(userPk)).orElseThrow(UserNotFoundExceptionCustom::new);
    }
}
