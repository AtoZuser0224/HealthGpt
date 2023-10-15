package uni.server.healthgpt.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import uni.server.healthgpt.repo.UserJpaRepo;
import uni.server.healthgpt.service.impl.FoodService;
import uni.server.healthgpt.service.impl.MailService;

import javax.transaction.Transactional;

@SpringBootTest
class FoodServiceTest {
    @Autowired
    private FoodService todayService;
    @Autowired
    private MailService mailService;

    @Test
    void Test() throws Exception {
        mailService.sendSimpleMessage("wagwag0224@gmail.com");
    }

}