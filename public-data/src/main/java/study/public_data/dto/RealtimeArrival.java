package study.public_data.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RealtimeArrival {
    private String updnLine; // 상행 / 하행
    private String trainLineNm; // 최종역 - 방면
    private String arvlMsg2; // 도착 시간
    private String arvlMsg3; // 곧 도착할 역 이름
    private String lstcarAt; // 막차 여부

    @Builder
    public RealtimeArrival(String updnLine, String trainLineNm, String arvlMsg2, String arvlMsg3, String lstcarAt) {
        this.updnLine = updnLine;
        this.trainLineNm = trainLineNm;
        this.arvlMsg2 = arvlMsg2;
        this.arvlMsg3 = arvlMsg3;
        this.lstcarAt = lstcarAt;
    }
}
