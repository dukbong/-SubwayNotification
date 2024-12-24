package study.main_server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import study.main_server.dto.RequestMessage;
import study.main_server.dto.UserInfo;

import static study.main_server.util.SerializationUtil.serialize;

@Service
@RequiredArgsConstructor
public class SubWayService {

    private final AmqpTemplate amqpTemplate;

    public void getSubWayInfo(String station, UserInfo userInfo) {
//                                        유효성 검사는 이미 Controller에서 처리됨
        if(StringUtils.hasText(station)/* && StringUtils.hasText(userInfo.getEmail())*/) {
            byte[] sendByteArray = serialize(new RequestMessage(station, userInfo));
            if(sendByteArray.length > 0) {
                amqpTemplate.convertAndSend("preSubway", sendByteArray);
            }
//            else {
//                직렬화 실패 시 처리
//            }
        }
    }

}
