package capstone.fullstack.api.local.commerce;

import capstone.fullstack.domain.rank.FloatingRentalFeeRank;
import capstone.fullstack.repository.local.rank.floating.FloatingGroupDto;
import capstone.fullstack.repository.local.rank.floating.FloatingRankRepository;
import capstone.fullstack.repository.local.rank.floating.FloatingRankDto;
import capstone.fullstack.repository.local.rank.rentalfee.RentalFeeGroupDto;
import capstone.fullstack.repository.local.rank.rentalfee.RentalFeeRankDto;
import capstone.fullstack.repository.local.rank.rentalfee.RentalFeeRankRepository;
import capstone.fullstack.service.local.commerce.rank.FloatingRentalFeeService;
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

    private final FloatingRankRepository floatingRankRepository;
    private final RentalFeeRankRepository rentalFeeRankRepository;
    private final FloatingRentalFeeService floatingRentalFeeService;

    @GetMapping("/api/rank/floating")
    public List<FloatingRankDto> findFloatingRank(){
        List<FloatingGroupDto> allFloatings = floatingRankRepository.findAllFloatingGroup();
        return getFloatingTopRank(allFloatings);
    }

    @GetMapping("/api/rank/floating/lower")
    public List<FloatingRankDto> findFloatingLowerRank(){
        List<FloatingGroupDto> allFloatings = floatingRankRepository.findAllFloatingGroup();
        return getFloatingLowerRank(allFloatings);
    }

    @GetMapping("/api/rank/rentalfee")
    public List<RentalFeeRankDto> findRentalFeeRank(){
        List<RentalFeeGroupDto> allRentalFees = rentalFeeRankRepository.findAllRentalFeeGroup();
        return getRentalFeeTopRank(allRentalFees);
    }

    @GetMapping("/api/rank/rentalfee/lower")
    public List<RentalFeeRankDto> findRentalFeeLowerRank(){
        List<RentalFeeGroupDto> allRentalFees = rentalFeeRankRepository.findAllRentalFeeGroup();
        return getRentalFeeLowerRank(allRentalFees);
    }

    @GetMapping("/api/rank/floating-rentalfee")
    public List<FloatingRentalFeeRank> findFloatingRentalFeeRank(){
        return getFloatingRentalfeeTopRank(floatingRentalFeeService.getFloatingRentalFeeRanks());
    }



    @GetMapping("/api/rank/floating-rentalfee/lower")
    public List<FloatingRentalFeeRank> findFloatingRentalFeeLowerRank(){
        return getFloatingRentalfeeLowerRank(floatingRentalFeeService.getFloatingRentalFeeRanks());
    }

    @GetMapping("/api/rank/floating-rentalfee/update")
    public Integer setFloatingRentalFeeRank(){
        List<FloatingGroupDto> allFloatings = floatingRankRepository.findFloatingGroupLastYear();
        List<RentalFeeGroupDto> allRentalFees = rentalFeeRankRepository.findRentalFeeGroupLastYear();

        Map<String, List<FloatingGroupDto>> floatingMap = allFloatings.stream().collect(Collectors.groupingBy(FloatingGroupDto::getDong));
        Map<String, List<RentalFeeGroupDto>> rentalFeeMap = allRentalFees.stream().collect(Collectors.groupingBy(RentalFeeGroupDto::getAreaName));

        List<FloatingRentalFeeRank> resultList = new ArrayList<>();
        for(String dong : floatingMap.keySet()){
            List<FloatingGroupDto> floatingGroups = floatingMap.get(dong);
            List<RentalFeeGroupDto> rentalFeeGroups = rentalFeeMap.get(dong);

            float value = (float)floatingGroups.get(0).getTotalFlpop() / rentalFeeGroups.get(0).getRentalFee();
            long floating = floatingGroups.get(0).getTotalFlpop() / floatingGroups.get(0).getCount();
            int rentalFee = rentalFeeGroups.get(0).getRentalFee() / 4;
            resultList.add(new FloatingRentalFeeRank(dong, value, floating, rentalFee));
        }
        // 정렬
        Collections.sort(resultList, Collections.reverseOrder());

        int rank = 1;
        for (FloatingRentalFeeRank area : resultList) {
            area.setRanking(rank++);
        }

        int num = floatingRentalFeeService.savaAll(resultList);

        return num;
    }

    private List<FloatingRentalFeeRank> getFloatingRentalfeeTopRank(List<FloatingRentalFeeRank> floatingRentalFeeRanks) {
        return floatingRentalFeeRanks.subList(0, 10);
    }

    private List<FloatingRentalFeeRank> getFloatingRentalfeeLowerRank(List<FloatingRentalFeeRank> floatingRentalFeeRanks) {
        Collections.sort(floatingRentalFeeRanks);

        int rank = 1;
        for(int i=0; i<10; i++){
            floatingRentalFeeRanks.get(i).setRanking(rank++);
        }
        return floatingRentalFeeRanks.subList(0, 10);
    }

    private static List<FloatingRankDto> getFloatingTopRank(List<FloatingGroupDto> allFloatings) {
        List<FloatingRankDto> resultList = getFloatingRankDtos(allFloatings);

        // 정렬
        Collections.sort(resultList, Collections.reverseOrder());

        int rank = 1;
        for (FloatingRankDto dong : resultList) {
            dong.setRank(rank++);
            if(rank > 10) break;
        }

        return resultList.subList(0, 10);
    }

    private static List<FloatingRankDto> getFloatingLowerRank(List<FloatingGroupDto> allFloatings) {
        List<FloatingRankDto> resultList = getFloatingRankDtos(allFloatings);

        // 정렬
        Collections.sort(resultList);

        int rank = 1;
        for (FloatingRankDto dong : resultList) {
            dong.setRank(rank++);
            if(rank > 10) break;
        }

        return resultList.subList(0, 10);
    }

    private static List<FloatingRankDto> getFloatingRankDtos(List<FloatingGroupDto> allFloatings) {
        Map<String, List<FloatingGroupDto>> collect = allFloatings.stream().collect(Collectors.groupingBy(FloatingGroupDto::getDong));
        // 유동인구 상승률 구하기
        List<FloatingRankDto> resultList = new ArrayList<>();
        for(String dong : collect.keySet()){
            List<FloatingGroupDto> dongs = collect.get(dong);

            float rate = (float)(dongs.get(0).getTotalFlpop() - dongs.get(1).getTotalFlpop()) / dongs.get(1).getTotalFlpop() * 100;
            log.info("rate={}", rate);
            long f22 = dongs.get(0).getTotalFlpop() / dongs.get(0).getCount();
            long f21 = dongs.get(1).getTotalFlpop() / dongs.get(1).getCount();
            resultList.add(new FloatingRankDto(dong, rate, f21, f22));
        }
        return resultList;
    }

    private static List<RentalFeeRankDto> getRentalFeeTopRank(List<RentalFeeGroupDto> allRentalFees) {
        List<RentalFeeRankDto> resultList = getRentalFeeRankDtos(allRentalFees);
        // 정렬
        Collections.sort(resultList, Collections.reverseOrder());

        int rank = 1;
        for (RentalFeeRankDto area : resultList) {
            area.setRank(rank++);
        }
        return resultList.subList(0, 10);
    }

    private static List<RentalFeeRankDto> getRentalFeeLowerRank(List<RentalFeeGroupDto> allRentalFees) {
        List<RentalFeeRankDto> resultList = getRentalFeeRankDtos(allRentalFees);
        // 정렬
        Collections.sort(resultList);

        int rank = 1;
        for (RentalFeeRankDto area : resultList) {
            area.setRank(rank++);
        }
        return resultList.subList(0, 10);
    }

    private static List<RentalFeeRankDto> getRentalFeeRankDtos(List<RentalFeeGroupDto> allRentalFees) {
        Map<String, List<RentalFeeGroupDto>> collect = allRentalFees.stream().collect(Collectors.groupingBy(RentalFeeGroupDto::getAreaName));

        List<RentalFeeRankDto> resultList = new ArrayList<>();
        for(String area : collect.keySet()){
            List<RentalFeeGroupDto> areas = collect.get(area);

            if(areas.size() <= 1)
                continue;

            float rate = (float) (areas.get(0).getRentalFee() - areas.get(1).getRentalFee()) / areas.get(1).getRentalFee() * 100;
            if(Float.isNaN(rate))
                continue;

            int fee22 = areas.get(0).getRentalFee() / 4;
            int fee21 = areas.get(1).getRentalFee() / 4;

            resultList.add(new RentalFeeRankDto(area, rate, fee21, fee22));
        }
        return resultList;
    }
}
