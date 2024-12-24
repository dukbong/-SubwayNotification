package study.public_data.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import study.public_data.dto.ApplyDto;
import study.public_data.dto.PublicDataResponse;

@Slf4j
@Component
@RequiredArgsConstructor
public class PublicDataService {

    private final WebClient.Builder webClientBuilder;
    private final AmqpTemplate amqpTemplate;
    private final Environment env;

    private static final ObjectMapper mapper = new ObjectMapper();

    @RabbitListener(queues = "preSubway")
    public void getPublicData(String preSubway) {
        try {
            ApplyDto applyDto = mapper.readValue(preSubway, ApplyDto.class);
            String url = "http://swopenapi.seoul.go.kr/api/subway/" +
                    env.getProperty("public_data.key") + "/json/realtimeStationArrival/0/999/" +
                    applyDto.getStatnNm();
            WebClient webClient = webClientBuilder.baseUrl(url).build();
            Mono<String> result = webClient.get()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(String.class);

            result.subscribe(response -> sendMessageToQueue(response, applyDto));
        } catch (Exception e){
            throw new ListenerExecutionFailedException("불러오는 과정에서 문제 발생", e);
        }
    }

    private void sendMessageToQueue(String response, ApplyDto applyDto) {
        try {
            PublicDataResponse publicDataResponse = mapper.readValue(response, PublicDataResponse.class);
            publicDataResponse.setEmail(applyDto.getEmail());
            publicDataResponse.setStation(applyDto.getStatnNm());
            amqpTemplate.convertAndSend("postSubway", publicDataResponse);
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

}
