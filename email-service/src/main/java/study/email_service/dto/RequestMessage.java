package study.email_service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@Getter @Setter
public class RequestMessage {
    private String email;
    private String station;
    private List<RealtimeArrival> realtimeArrivalList;
}