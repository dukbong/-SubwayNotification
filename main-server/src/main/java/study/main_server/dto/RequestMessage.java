package study.main_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestMessage {
    private String station;
    private UserInfo userInfo;
}
