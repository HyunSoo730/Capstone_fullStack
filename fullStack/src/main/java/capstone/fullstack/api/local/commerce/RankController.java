package capstone.fullstack.api.local.commerce;

import capstone.fullstack.repository.local.commerce.population.FloatingRepository;
import capstone.fullstack.repository.local.rank.floating.FloatingGroupDto;
import capstone.fullstack.repository.local.rank.floating.FloatingRankRepository;
import capstone.fullstack.repository.local.rank.floating.FloatingRankDto;
import capstone.fullstack.repository.local.rank.rentalfee.RentalFeeGroupDto;
import capstone.fullstack.repository.local.rank.rentalfee.RentalFeeRankDto;
import capstone.fullstack.repository.local.rank.rentalfee.RentalFeeRankRepository;
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
    private final FloatingRankRepository floatingRankRepository;
    private final RentalFeeRankRepository rentalFeeRankRepository;

    @GetMapping("/api/rank/floating")
    public List<FloatingRankDto> findFloatingRank(){
        List<FloatingGroupDto> allFloatings = floatingRankRepository.findAllFloatingWithArea();

        Map<String, List<FloatingGroupDto>> collect = allFloatings.stream().collect(Collectors.groupingBy(FloatingGroupDto::getDong));
        // 유동인구 상승률 구하기
        List<FloatingRankDto> resultList = new ArrayList<>();
        for(String dong : collect.keySet()){
            List<FloatingGroupDto> dongs = collect.get(dong);

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

    @GetMapping("/api/rank/rentalfee")
    public List<RentalFeeRankDto> findRentalFeeRank(){
        List<RentalFeeGroupDto> allRentalFees = rentalFeeRankRepository.findAllRentalFeeGroup();

        Map<String, List<RentalFeeGroupDto>> collect = allRentalFees.stream().collect(Collectors.groupingBy(RentalFeeGroupDto::getAreaName));

        List<RentalFeeRankDto> resultList = new ArrayList<>();
        for(String area : collect.keySet()){
            List<RentalFeeGroupDto> areas = collect.get(area);

            float rate = 0.f;
            if(areas.size() > 1)
                rate = (float) (areas.get(0).getRentalFee() - areas.get(1).getRentalFee()) / areas.get(1).getRentalFee() * 100;
            if(Float.isNaN(rate))
                rate = 0.0f;
            resultList.add(new RentalFeeRankDto(areas.get(0).getAreaName(), rate));
        }
        // 정렬
        Collections.sort(resultList, Collections.reverseOrder());

        int rank = 1;
        for (RentalFeeRankDto area : resultList) {
            area.setRank(rank++);
        }
        return resultList;
    }
}
