package capstone.fullstack.api.local.commerce;


import capstone.fullstack.domain.AvgOperationPeriod;
import capstone.fullstack.domain.RentalFee;
import capstone.fullstack.domain.population.FloatingPopulation;
import capstone.fullstack.repository.local.commerce.AvgOperationPeriodRepository;
import capstone.fullstack.repository.local.commerce.RentalFeeRepository;
import capstone.fullstack.repository.local.commerce.population.FloatingRepository;
import capstone.fullstack.repository.local.custom.LocalSimpleRepository;
import capstone.fullstack.resultvo.AvgOperationPeriodVO;
import capstone.fullstack.resultvo.FloatingPopulationVO;
import capstone.fullstack.resultvo.RentalFeeVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class TotalLocalCommercialController {

    private final RentalFeeRepository rentalFeeRepository;
    private final AvgOperationPeriodRepository avgOperationPeriodRepository;
    private final FloatingRepository floatingRepository;

    private final LocalSimpleRepository localSimpleRepository;


    /**
     * @param guName 지역구
     * @param dongName 행정동
     * @return 해당 지역(행정동)에 대한 임대로 2년치 총 8분기 데이터
     */
    @GetMapping("/api/local-commerce/total/rentalfee/{guName}/{dongName}")
    public List<RentalFeeVO> getRentalFeeForTotalDong(@PathVariable String guName, @PathVariable String dongName) {
        List<RentalFeeVO> result = new ArrayList<>();

        List<RentalFee> rentalFee = rentalFeeRepository.findRentalFeeForTotalDong(guName, findDongName(dongName));

        Map<Integer, List<RentalFee>> groupByYear = rentalFee.stream().collect(Collectors.groupingBy(RentalFee::getYear));
        List<Integer> years = groupByYear.keySet().stream().sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        for (int y = 0; y < years.size(); y++) {
            int year = years.get(y);
            List<RentalFee> sameYearRentalFee = groupByYear.get(year);
            Map<Integer, List<RentalFee>> groupByQuarter = sameYearRentalFee
                    .stream().collect(Collectors.groupingBy(RentalFee::getQuarter));

            List<Integer> quarters = groupByQuarter.keySet()
                    .stream().sorted()
                    .collect(Collectors.toList());
            for (int q = 0; q < quarters.size(); q++) {
                int quarter = quarters.get(q);
//                log.info("groupByQuarter.get={}", groupByQuarter.get(quarter).size());
                List<RentalFee> fees = groupByQuarter.get(quarter);
                result.add(new RentalFeeVO(year, quarter, fees));
            }
        }
        return result;
    }

    /**
     * @param guName 지역구 이름
     * @param dongName 행정동 이름
     * @return 행정동에 대한 평균 영업 기간 (업종 전체 평균)
     */
    @GetMapping("/api/local-commerce/total/operation/{guName}/{dongName}")
    public AvgOperationPeriodVO getAvgOperationPeriodForTotalDong(@PathVariable String guName, @PathVariable String dongName) {

        List<AvgOperationPeriod> periods = avgOperationPeriodRepository.findAvgPeriodByAreaNameForTotalDong(guName, findDongName(dongName));

        return new AvgOperationPeriodVO(periods);
    }

    /**
     * @param guName 지역구 이름
     * @param dongName 행정동 이름
     * @param serviceName 직종 이름
     * @return 행정동에서의 해당 직종의 평균 영업 기간
     */
    @GetMapping("/api/local-commerce/total/operation/{guName}/{dongName}/{serviceName}")
    public AvgOperationPeriodVO getAvgOperationPeriodWithServiceNameForTotalDong(@PathVariable String guName, @PathVariable String dongName, @PathVariable String serviceName) {

        List<AvgOperationPeriod> periods = avgOperationPeriodRepository.findAvgPeriodByAreaNameAndServiceNameForTotalDong(guName, findDongName(dongName), serviceName);

        return new AvgOperationPeriodVO(periods);
    }

    /**
     * @param dongName 행정동
     * @return 행정동에 대한 유동인구 22년, 21년 분기별로
     */
    @GetMapping("/api/local-commerce/total/floating/{guName}/{dongName}")
    public List<FloatingPopulationVO> getFloatingPopulationForTotalDong(@PathVariable String guName, @PathVariable String dongName) {

        //행정동에 대한 상권코드들
        List<Integer> commercialCodes = localSimpleRepository.findCommercialCodesCustom(guName, findDongName(dongName));

        List<FloatingPopulationVO> result = new ArrayList<>();

        List<FloatingPopulation> floatingPopulation = floatingRepository.findFloatingPopulation(commercialCodes);

        Map<Integer, List<FloatingPopulation>> groupByYear = floatingPopulation.stream().collect(Collectors.groupingBy(FloatingPopulation::getYear));
        List<Integer> years = groupByYear.keySet().stream().sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        for (int y = 0; y < years.size(); y++) {
            int year = years.get(y);
            List<FloatingPopulation> sameYearPops = groupByYear.get(year);
            Map<Integer, List<FloatingPopulation>> groupByQuarter = sameYearPops
                    .stream().collect(Collectors.groupingBy(FloatingPopulation::getQuarter));

            List<Integer> quarters = groupByQuarter.keySet()
                    .stream().sorted()
                    .collect(Collectors.toList());
            for (int q = 0; q < quarters.size(); q++) {
                int quarter = quarters.get(q);
//                log.info("year={}, quarter={}", year, quarter);
                result.add(new FloatingPopulationVO(groupByQuarter.get(quarter)));
            }
        }

        return result;
    }

    public String findDongName(String dong){
        String findDong;
        if(dong.equals("여의도")){
            findDong = dong.substring(0, dong.length() - 2);
        }
        else{
            findDong = dong.substring(0, dong.length() - 1);
        }
        return findDong;
    }

}
