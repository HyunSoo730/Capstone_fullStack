package capstone.fullstack.service;

import capstone.fullstack.dto.RevenueDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RevenueApiService {
    //base url을 추가하여 생성
    private String base_url = "http://openapi.seoul.go.kr:8088";
    private final String API_KEY = "6b716c716368797537356648694a4f"; //인증키
    private final String type = "json";
    private String serviceName = "youtubeTrend";
    private WebClient webclient = WebClient.create(base_url);


    public List<RevenueDto> get() {
        List<RevenueDto> result = webclient.get()
                .uri(uriBuilder -> uriBuilder   //base_uri 뒤에 만들어질 URI를 만들어야지.
                        .pathSegment(API_KEY, type, serviceName)
//                        .queryParam("START_INDEX", 1)   //queryParam으로 해당 API를 호출할 조건들을 같이 넣어줌.
//                        .queryParam("END_INDEX", 5)
                        .path("/1/5")
                        .build())
                .retrieve()   //응답 받을 값이 여러개이면 FLux로, 응답받을 값이 한개면 Mono()로 받아오자.
                .bodyToFlux(RevenueDto.class)                 //한번의 호출만 할 것이라면 block()을 사
                .toStream().collect(Collectors.toList());
        log.info("result 정보 {}", result);
        return result;
//        List<Object> result = webclient.get()
//                .uri(uriBuilder -> uriBuilder   //base_uri 뒤에 만들어질 URI를 만들어야지.
//                        .pathSegment(API_KEY, type, serviceName)
//                        .path("/1/5")
//                        .build())
//                .retrieve()   //응답 받을 값이 여러개이면 FLux로, 응답받을 값이 한개면 Mono()로 받아오자.
//                .bodyToFlux(Object.class)                 //한번의 호출만 할 것이라면 block()을 사
//                .toStream().collect(Collectors.toList());
//        log.info("결과 확인 {}", result);
//        return result;

    }
}
