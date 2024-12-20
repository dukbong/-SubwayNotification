package study.public_data.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PublicDataServiceTest {

    @Autowired
    private PublicDataService publicDataService;

    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    void publicDataTest() throws JsonProcessingException, InterruptedException {
        // given
        TestDTO test = new TestDTO();
        test.setEmail("test@test.com");
        test.setStatnNm("사당");
        String applyMessage = mapper.writeValueAsString(test);
        // 매개변수로 test를 json으로 변경해서 보내야한다.
        publicDataService.getPublicData(applyMessage);
        Thread.sleep(1000);
    }


}

    class TestDTO {
        private String email;
        private String statnNm;

        public TestDTO(){}

        public void setEmail(String email) {
            this.email = email;
        }
        public void setStatnNm(String statnNm) {
            this.statnNm = statnNm;
        }
        public String getEmail() {
            return email;
        }

        public String getStatnNm() {
            return statnNm;
        }
    }
