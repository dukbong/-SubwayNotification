package study.email_service.email;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import study.email_service.dto.RequestMessage;

@Component
@RequiredArgsConstructor
public class EmailService {

    private final AmqpTemplate amqpTemplate;
//    private final JavaMailSender mailSender;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @RabbitListener(queues = "postSubway")
    public void sendEmail(String postSubwayMessage) {
        try {
            RequestMessage requestMessage = objectMapper.readValue(postSubwayMessage, RequestMessage.class);
//            MimeMessage message = mailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(message, true);

//            helper.setFrom("your-email@gmail.com");  // 발신자 이메일
//            helper.setTo(requestMessage.getEmail());                        // 수신자 이메일
//            helper.setSubject(requestMessage.getStation() + " 역 지하철 도착 정보");              // 제목
//            helper.setText(text, true);               // 본문 (true -> HTML 형태로 전송)

//            mailSender.send(message);
        } catch (Exception e) {
            throw new ListenerExecutionFailedException(">>>>>>>>>>>>>>>> 메시지 처리 실패", e);
        }
    }

}
