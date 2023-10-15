package uni.server.healthgpt.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import uni.server.healthgpt.data.entity.MailPass;
import uni.server.healthgpt.repo.MailPassRepo;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

@Service
public class MailService {
    @Autowired
    JavaMailSender emailsender; // Bean 등록해둔 MailConfig 를 emailsender 라는 이름으로 autowired
    @Autowired
    MailPassRepo mailPassRepo;
    private String ePw; // 인증번호

    // 메일 내용 작성
    public MimeMessage createMessage(String to) throws MessagingException, UnsupportedEncodingException {
//		System.out.println("보내는 대상 : " + to);
//		System.out.println("인증 번호 : " + ePw);

        MimeMessage message = emailsender.createMimeMessage();

        message.addRecipients(MimeMessage.RecipientType.TO, to);// 보내는 대상
        message.setSubject("ChatFit 회원가입 이메일 인증");// 제목

        String msg="";
        msg += "<h1 style=\"font-size: 30px; padding-right: 30px; padding-left: 30px;\">이메일 주소 확인</h1>";
        msg += "<p style=\"font-size: 17px; padding-right: 30px; padding-left: 30px;\">아래 확인 코드를 회원가입 화면에서 입력해주세요.</p>";
        msg += "<div style=\"padding-right: 30px; padding-left: 30px; margin: 32px 0 40px;\"><table style=\"border-collapse: collapse; border: 0; background-color: #F4F4F4; height: 70px; table-layout: fixed; word-wrap: break-word; border-radius: 6px;\"><tbody><tr><td style=\"text-align: center; vertical-align: middle; font-size: 30px;\">";
        msg += ePw;
        msg += "</td></tr></tbody></table></div>";

        message.setText(msg, "utf-8", "html"); //내용, charset타입, subtype
        // 보내는 사람의 이메일 주소, 보내는 사람 이름
        message.setFrom(new InternetAddress("wag0224@naver.com", "ChatFit"));// 보내는 사람

        return message;
    }

    // 랜덤 인증 코드 전송
    public String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 4; i++) { // 인증코드 8자리
            int index = 2;

            switch (index) {
                case 0:
                    key.append((char) ((int) (rnd.nextInt(26)) + 97));
                    // a~z (ex. 1+97=98 => (char)98 = 'b')
                    break;
                case 1:
                    key.append((char) ((int) (rnd.nextInt(26)) + 65));
                    // A~Z
                    break;
                case 2:
                    key.append((rnd.nextInt(10)));
                    // 0~9
                    break;
            }
        }

        return key.toString();
    }

    // 메일 발송
    // sendSimpleMessage 의 매개변수로 들어온 to 는 곧 이메일 주소가 되고,
    // MimeMessage 객체 안에 내가 전송할 메일의 내용을 담는다.
    // 그리고 bean 으로 등록해둔 javaMail 객체를 사용해서 이메일 send!!
    public String sendSimpleMessage(String to) throws Exception {

        ePw = createKey(); // 랜덤 인증번호 생성

        MimeMessage message = createMessage(to); // 메일 발송
        try {// 예외처리
            emailsender.send(message);
        } catch (MailException es) {
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
        MailPass mailPass = MailPass.builder()
                .email(to)
                .code(ePw)
                .date(LocalDateTime.now())
                .build();
        mailPassRepo.save(mailPass);
        return "success";
    }
    public boolean isMail(String to,String code){
        if (mailPassRepo.findById(to).isEmpty()){
            return false;
        }
        LocalDateTime date = mailPassRepo.findById(to).get().getDate();
        LocalDateTime now = LocalDateTime.now();
        return now.getDayOfYear() == date.getDayOfYear() && now.getMinute() <= date.getMinute() + 4 && Objects.equals(mailPassRepo.findById(to).get().getCode(), code);
    }
}