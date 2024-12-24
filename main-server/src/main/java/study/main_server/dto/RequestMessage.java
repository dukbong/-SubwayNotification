package study.main_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestMessage implements Serializable {

    @Serial
    private static final long serialVersionUID = -45648654L;

    private String station;
    private UserInfo userInfo;
}
