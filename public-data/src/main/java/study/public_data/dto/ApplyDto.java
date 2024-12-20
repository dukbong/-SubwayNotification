package study.public_data.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class ApplyDto {
    private String statnNm; // 지하철역명
    private String email;

    public ApplyDto(String statnNm, String email) {
        this.statnNm = statnNm;
        this.email = email;
    }
}
