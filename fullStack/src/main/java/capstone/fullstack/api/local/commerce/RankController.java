package capstone.fullstack.api.local.commerce;

import capstone.fullstack.domain.Local;
import capstone.fullstack.domain.population.FloatingPopulation;
import capstone.fullstack.repository.local.commerce.LocalRepository;
import capstone.fullstack.repository.local.commerce.population.FloatingRepository;
import capstone.fullstack.repository.local.rank.floating.FloatingAddAreaDto;
import capstone.fullstack.repository.local.rank.floating.FloatingAddAreaRepository;
import capstone.fullstack.repository.local.rank.floating.FloatingRankDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RankController {

    private final FloatingRepository floatingRepository;
    private final FloatingAddAreaRepository floatingAddAreaRepository;

    @GetMapping("/api/rank/floating")
    public List<FloatingRankDto> insertFloatingRank(){
        List<FloatingAddAreaDto> allFloatings = floatingAddAreaRepository.findAllFloatingWithArea();

        Map<String, List<FloatingAddAreaDto>> collect = allFloatings.stream().collect(Collectors.groupingBy(FloatingAddAreaDto::getDong));
        // 유동인구 상승률 구하기
        List<FloatingRankDto> resultList = new ArrayList<>();
        for(String dong : collect.keySet()){
            List<FloatingAddAreaDto> dongs = collect.get(dong);

            float rate = (float)(dongs.get(0).getTotalFlpop() - dongs.get(1).getTotalFlpop()) / dongs.get(1).getTotalFlpop() * 100;
            log.info("rate={}", rate);
            resultList.add(new FloatingRankDto(dongs.get(0).getDong(), rate));
        }

        // 정렬
        Collections.sort(resultList, Collections.reverseOrder());

        int rank = 1;
        for (FloatingRankDto dong : resultList) {
            dong.setRank(rank++);
        }

        return resultList;
    }
}
