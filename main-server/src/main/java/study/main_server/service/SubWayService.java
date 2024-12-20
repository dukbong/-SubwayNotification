package study.main_server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import study.main_server.dto.RequestMessage;
import study.main_server.dto.UserInfo;

@Service
@RequiredArgsConstructor
public class SubWayService {

    private final AmqpTemplate amqpTemplate;

    public void getSubWayInfo(String station, UserInfo userInfo) {
        RequestMessage requestMessage = null;
        if(StringUtils.hasText(station) && StringUtils.hasText(userInfo.getEmail())) {
            requestMessage = new RequestMessage(station, userInfo);
        }

        amqpTemplate.convertAndSend("preSubway", requestMessage);
    }

}
