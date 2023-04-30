package capstone.fullstack.api.local.commerce;


import capstone.fullstack.domain.AvgOperationPeriod;
import capstone.fullstack.domain.RentalFee;
import capstone.fullstack.domain.population.FloatingPopulation;
import capstone.fullstack.repository.local.commerce.AvgOperationPeriodRepository;
import capstone.fullstack.repository.local.commerce.RentalFeeRepository;
import capstone.fullstack.repository.local.commerce.population.FloatingRepository;
import capstone.fullstack.resultvo.*;
import capstone.fullstack.service.local.commerce.all.AllService;
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
public class LocalCommercialController {

    private final RentalFeeRepository rentalFeeRepository;
    private final AvgOperationPeriodRepository avgOperationPeriodRepository;
    private final FloatingRepository floatingRepository;

    private final AllService allService;


    /**
     * @param areaName 행정동
     * @return 해당 지역(행정동)에 대한 임대로 2년치 총 8분기 데이터
     */
    @GetMapping("/api/local-commerce/rentalfee/{areaName}")
    public List<RentalFeeVO> getRentalRee(@PathVariable String areaName) {
        List<RentalFeeVO> result = new ArrayList<>();

        List<RentalFee> rentalFee = rentalFeeRepository.findRentalFee(areaName);

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
                RentalFee fee = groupByQuarter.get(quarter).get(0);
                result.add(new RentalFeeVO(year, quarter, fee.getRentalfee_total(), fee.getRentalfee_1st_floor(), fee.getRentalfee_except_1st_floor()));
            }
        }
        return result;
    }

    /**
     * @param areaName 행정동
     * @return 행정동에 대한 평균 영업 기간 (업종 전체 평균)
     */
    @GetMapping("/api/local-commerce/operation/{areaName}")
    public AvgOperationPeriodVO getAvgOperationPeriod(@PathVariable String areaName) {

        AvgOperationPeriod period = avgOperationPeriodRepository.findAvgPeriodByAreaName(areaName);

        return new AvgOperationPeriodVO(period.getAvg_period());
    }

    /**
     * @param areaName    행정동
     * @param serviceName 직종 이름
     * @return 행정동에서의 해당 직종의 평균 영업 기간
     */
    @GetMapping("/api/local-commerce/operation/{areaName}/{serviceName}")
    public AvgOperationPeriodVO getAvgOperationPeriodWithServiceName(@PathVariable String areaName, @PathVariable String serviceName) {

        AvgOperationPeriod period = avgOperationPeriodRepository.findAvgPeriodByAreaNameAndServiceName(areaName, serviceName);

        return new AvgOperationPeriodVO(period.getAvg_period());
    }

    /**
     * @param areaName 행정동
     * @return 행정동에 대한 유동인구 22년, 21년 분기별로
     */
    @GetMapping("/api/local-commerce/floating/{areaName}")
    public List<FloatingPopulationVO> getFloatingPopulation(@PathVariable String areaName) {

        //행정동에 대한 상권코드들
        List<Integer> commercialCodes = allService.findAllCommercialCode(areaName);

        List<FloatingPopulationVO> result = new ArrayList<>();

        List<FloatingPopulation> floatingPopulation = floatingRepository.findFloatingPopulation(commercialCodes);
//        Map<Integer, List<FloatingPopulation>> groupByYear = floatingPopulation.stream().collect(Collectors.groupingBy(FloatingPopulation::getYear));
//
//        groupByYear.keySet()
//                .stream().sorted(Comparator.reverseOrder())
//                .forEach(
//                        y -> groupByYear.get(y)
//                                .stream().collect(Collectors.groupingBy(FloatingPopulation::getQuarter))
//                );
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
                result.add(sumFloatingPopulation(groupByQuarter.get(quarter), year, quarter));
            }
        }

        return result;
    }

    /**
     *
     * @param commercialCode 상권코드
     * @return  상권코드에 대한 유동인구 2년치 반환
     */
    @GetMapping("/api/single-commerce/floating/{commercialCode}")
    public List<FloatingPopulationVO> getFloatingPopsFromCommercial(@PathVariable Integer commercialCode) {
        List<FloatingPopulation> floatingPopulation = floatingRepository.findFloatingPopulationInCommercial(commercialCode);

        List<FloatingPopulationVO> result = new ArrayList<>();

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
                result.add(sumFloatingPopulation(groupByQuarter.get(quarter), year, quarter));
            }
        }
        return result;
    }

    protected FloatingPopulationVO sumFloatingPopulation(List<FloatingPopulation> populations, int year,
                                                         int quarter) {
        FloatingPopulationVO result = new FloatingPopulationVO();

        result.setYear(year);
        result.setQuarter(quarter);
        result.setTotal_flpop(populations.stream().mapToInt(FloatingPopulation::getTotal_flpop).sum());
        result.setMale_flpop(populations.stream().mapToInt(FloatingPopulation::getMale_flpop).sum());
        result.setFemale_flpop(populations.stream().mapToInt(FloatingPopulation::getFemale_flpop).sum());

        result.setAge_10_flpop(populations.stream().mapToInt(FloatingPopulation::getAge_10_flpop).sum());
        result.setAge_20_flpop(populations.stream().mapToInt(FloatingPopulation::getAge_20_flpop).sum());
        result.setAge_30_flpop(populations.stream().mapToInt(FloatingPopulation::getAge_30_flpop).sum());
        result.setAge_40_flpop(populations.stream().mapToInt(FloatingPopulation::getAge_40_flpop).sum());
        result.setAge_50_flpop(populations.stream().mapToInt(FloatingPopulation::getAge_50_flpop).sum());
        result.setAge_60_flpop(populations.stream().mapToInt(FloatingPopulation::getAge_60_flpop).sum());

        result.setMon_flpop(populations.stream().mapToInt(FloatingPopulation::getMon_flpop).sum());
        result.setTue_flpop(populations.stream().mapToInt(FloatingPopulation::getTue_flpop).sum());
        result.setWed_flpop(populations.stream().mapToInt(FloatingPopulation::getWed_flpop).sum());
        result.setThu_flpop(populations.stream().mapToInt(FloatingPopulation::getThu_flpop).sum());
        result.setFri_flpop(populations.stream().mapToInt(FloatingPopulation::getFri_flpop).sum());
        result.setSat_flpop(populations.stream().mapToInt(FloatingPopulation::getSat_flpop).sum());
        result.setSun_flpop(populations.stream().mapToInt(FloatingPopulation::getSun_flpop).sum());

        result.setTime_1_flpop(populations.stream().mapToInt(FloatingPopulation::getTime_1_flpop).sum());
        result.setTime_2_flpop(populations.stream().mapToInt(FloatingPopulation::getTime_2_flpop).sum());
        result.setTime_3_flpop(populations.stream().mapToInt(FloatingPopulation::getTime_3_flpop).sum());
        result.setTime_4_flpop(populations.stream().mapToInt(FloatingPopulation::getTime_4_flpop).sum());
        result.setTime_5_flpop(populations.stream().mapToInt(FloatingPopulation::getTime_5_flpop).sum());
        result.setTime_6_flpop(populations.stream().mapToInt(FloatingPopulation::getTime_6_flpop).sum());

        return result;
    }
}
