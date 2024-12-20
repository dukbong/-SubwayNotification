package study.public_data.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@Getter @Setter
public class PublicDataResponse {
    private String email;
    private String station;
    private List<RealtimeArrival> realtimeArrivalList;
}
