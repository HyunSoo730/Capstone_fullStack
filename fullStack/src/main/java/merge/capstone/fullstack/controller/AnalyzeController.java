package merge.capstone.fullstack.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import merge.capstone.fullstack.domain.*;
import merge.capstone.fullstack.repository.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;


@Slf4j
@RestController
@RequiredArgsConstructor
public class AnalyzeController {

    private final LocalRepository localRepository;
    private final SalesRepository salesRepository;
    private final IndustryAnalysisRepository industryAnalysisRepository;
    private final CommerceChangeRepository commerceChangeRepository;
    private final IncomeConsumptionRepository incomeConsumptionRepository;
    private final PopulationRepository populationRepository;
    private final FacilityRepository facilityRepository;
    private final RentalFeeRepository rentalFeeRepository;

    /**
     *
     * @param commercialCode 상권코드
     * @return  상권코드에 대한 유동인구 2년치 반환
     */
    @GetMapping("/api/analyze/commercial/floating/{commercialCode}")
    public List<FloatingPopulationResponse> getFloatingPopsFromCommercial(@PathVariable Integer commercialCode) {
        List<FloatingPopulation> floatingPopulation = populationRepository.findFloatingPopulationInCommercial(commercialCode);

        List<FloatingPopulationResponse> result = new ArrayList<>();

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

    /**
     * @param areaName 행정동
     * @return 해당 지역(행정동)에 대한 임대로 2년치 총 8분기 데이터
     */
    @GetMapping("/api/analyze/rentalfee/{areaName}")
    public List<RentalFeeResponse> getRentalRee(@PathVariable String areaName) {
        List<RentalFeeResponse> result = new ArrayList<>();

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
                result.add(new RentalFeeResponse(year, quarter, fee.getRentalfee_total(), fee.getRentalfee_1st_floor(), fee.getRentalfee_except_1st_floor()));
            }
        }
        return result;
    }

    /**
     * @param areaName 행정동
     * @return 행정동에 대한 평균 영업 기간 (업종 전체 평균)
     */
    @GetMapping("/api/analyze/operation/{areaName}")
    public AvgOperationPeriodResponse getAvgOperationPeriod(@PathVariable String areaName) {

        AvgOperationPeriod period = industryAnalysisRepository.findAvgPeriodByAreaName(areaName);

        return new AvgOperationPeriodResponse(period.getAvg_period());
    }

    /**
     * @param areaName    행정동
     * @param serviceName 직종 이름
     * @return 행정동에서의 해당 직종의 평균 영업 기간
     */
    @GetMapping("/api/analyze/operation/{areaName}/{serviceName}")
    public AvgOperationPeriodResponse getAvgOperationPeriodWithServiceName(@PathVariable String
                                                                                   areaName, @PathVariable String serviceName) {

        AvgOperationPeriod period = industryAnalysisRepository.findAvgPeriodByAreaNameAndServiceName(areaName, serviceName);

        return new AvgOperationPeriodResponse(period.getAvg_period());
    }

    /**
     * @param areaName 행정동 이름
     * @return 행정동에 대한 유동인구 22년, 21년 분기별로
     */
    @GetMapping("/api/analyze/floating/{areaName}")
    public List<FloatingPopulationResponse> getFloatingPopulation(@PathVariable String areaName) {

        List<Integer> commercialCodes = localRepository.findCodesByAreaName(areaName);  //행정동에 대한 상권코드들

        List<FloatingPopulationResponse> result = new ArrayList<>();

        List<FloatingPopulation> floatingPopulation = populationRepository.findFloatingPopulation(commercialCodes);
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

    @GetMapping("/api/analyze/{areaName}")
    public TotalData getAnalysisDataFromAreaName(@PathVariable String areaName) {
        TotalData result = new TotalData<>();

        BasicData basicData = new BasicData(areaName, 2022, 4, null);
        result.setBasicData(basicData);

        List<Integer> commercialCodes = localRepository.findCodesByAreaName(areaName);

        for (int i = 4; i >= 1; i--) {
            List<Sales> salesList = salesRepository.findByCommercialCodes(commercialCodes, 2022, i);
            result.getSales().add(sumSales(salesList, 2022, i));

            List<IndustryAnalysis> industryList = industryAnalysisRepository.findIndustryAnalysis(commercialCodes, 2022, i);
            result.getIndustry().add(sumIndustry(industryList, 2022, i));
        }

        AvgOperationPeriod avgPeriod = industryAnalysisRepository.findAvgPeriodByAreaName(areaName);
        result.setAvgPeriod(new AvgOperationPeriodResponse(avgPeriod.getAvg_period()));

        CommerceChange commerceChange = commerceChangeRepository.findCommerceChange(areaName);
        result.setCommerceChange(commerceChange.getCommerce_metrics());

        IncomeConsumption incomeConsumption = incomeConsumptionRepository.findIncomeConsumption(commercialCodes);
        result.setIncomeConsumption(new IncomeConsumptionResponse(incomeConsumption.getAverage_monthly_income(), incomeConsumption.getTotal_amount_spent()));

        List<FloatingPopulation> floatingPopulation = populationRepository.findFloatingPopulation(commercialCodes);
        result.getPopulation().add(sumFloatingPopulation(floatingPopulation, 2022, 4));

        List<ResidentPopulation> residentPopulations = populationRepository.findResidentPopulation(commercialCodes);
        result.getPopulation().add(sumResidentPopulation(residentPopulations));

        List<WorkplacePopulation> workplacePopulations = populationRepository.findWorkplacePopulation(commercialCodes);
        result.getPopulation().add(sumWorkplacePopulation(workplacePopulations));

        List<Facility> facilityList = facilityRepository.findFacility(commercialCodes);
        result.setFacility(sumFacilities(facilityList));

        return result;
    }

    @GetMapping("/api/analyze/{areaName}/{serviceName}")
    public TotalData getAnalysisDataFromAreaNameAndServiceName(@PathVariable String areaName, @PathVariable String
            serviceName) {
        TotalData result = new TotalData<>();

        BasicData basicData = new BasicData(areaName, 2022, 4, serviceName);
        result.setBasicData(basicData);

        List<Integer> commercialCodes = localRepository.findCodesByAreaName(areaName);

        for (int i = 4; i >= 1; i--) {
            List<Sales> salesList = salesRepository.findByCommercialCodesAndServiceName(commercialCodes, serviceName, 2022, i);
            result.getSales().add(sumSales(salesList, 2022, i));

            List<IndustryAnalysis> industryList = industryAnalysisRepository.findIndustryAnalysisWithServiceName(commercialCodes, serviceName, 2022, i);
            result.getIndustry().add(sumIndustry(industryList, 2022, i));
        }
        AvgOperationPeriod avgPeriod = industryAnalysisRepository.findAvgPeriodByAreaNameAndServiceName(areaName, serviceName);
        result.setAvgPeriod(new AvgOperationPeriodResponse(avgPeriod.getAvg_period()));

        CommerceChange commerceChange = commerceChangeRepository.findCommerceChange(areaName);
        result.setCommerceChange(commerceChange.getCommerce_metrics());

        IncomeConsumption incomeConsumption = incomeConsumptionRepository.findIncomeConsumption(commercialCodes);
        result.setIncomeConsumption(new IncomeConsumptionResponse(incomeConsumption.getAverage_monthly_income(), incomeConsumption.getTotal_amount_spent()));

        List<FloatingPopulation> floatingPopulation = populationRepository.findFloatingPopulation(commercialCodes);
        result.getPopulation().add(sumFloatingPopulation(floatingPopulation, 2022, 4));

        List<ResidentPopulation> residentPopulations = populationRepository.findResidentPopulation(commercialCodes);
        result.getPopulation().add(sumResidentPopulation(residentPopulations));

        List<WorkplacePopulation> workplacePopulations = populationRepository.findWorkplacePopulation(commercialCodes);
        result.getPopulation().add(sumWorkplacePopulation(workplacePopulations));

        List<Facility> facilityList = facilityRepository.findFacility(commercialCodes);
        result.setFacility(sumFacilities(facilityList));

        return result;

    }

    protected FacilityResponse sumFacilities(List<Facility> facilities) {
        FacilityResponse result = new FacilityResponse();

        result.setNum_of_facility(facilities.stream().mapToInt(Facility::getNum_of_facility).sum());
        result.setNum_of_bank(facilities.stream().mapToInt(Facility::getNum_of_bank).sum());
        result.setNum_of_airport(facilities.stream().mapToInt(Facility::getNum_of_airport).sum());
        result.setNum_of_accommodation(facilities.stream().mapToInt(Facility::getNum_of_accommodation).sum());
        result.setNum_of_bus_stop(facilities.stream().mapToInt(Facility::getNum_of_bus_stop).sum());
        result.setNum_of_bus_terminal(facilities.stream().mapToInt(Facility::getNum_of_bus_terminal).sum());
        result.setNum_of_department_store(facilities.stream().mapToInt(Facility::getNum_of_department_store).sum());
        result.setNum_of_elementary_school(facilities.stream().mapToInt(Facility::getNum_of_elementary_school).sum());
        result.setNum_of_general_hospital(facilities.stream().mapToInt(Facility::getNum_of_general_hospital).sum());
        result.setNum_of_government_office(facilities.stream().mapToInt(Facility::getNum_of_government_office).sum());
        result.setNum_of_high_school(facilities.stream().mapToInt(Facility::getNum_of_high_school).sum());
        result.setNum_of_hospital(facilities.stream().mapToInt(Facility::getNum_of_hospital).sum());
        result.setNum_of_kindergarten(facilities.stream().mapToInt(Facility::getNum_of_kindergarten).sum());
        result.setNum_of_middle_school(facilities.stream().mapToInt(Facility::getNum_of_middle_school).sum());
        result.setNum_of_pharmacy(facilities.stream().mapToInt(Facility::getNum_of_pharmacy).sum());
        result.setNum_of_rail_station(facilities.stream().mapToInt(Facility::getNum_of_rail_station).sum());
        result.setNum_of_subway(facilities.stream().mapToInt(Facility::getNum_of_subway).sum());
        result.setNum_of_supermarket(facilities.stream().mapToInt(Facility::getNum_of_supermarket).sum());
        result.setNum_of_theater(facilities.stream().mapToInt(Facility::getNum_of_theater).sum());
        result.setNum_of_university(facilities.stream().mapToInt(Facility::getNum_of_university).sum());

        return result;
    }

    protected FloatingPopulationResponse sumFloatingPopulation(List<FloatingPopulation> populations, int year,
                                                               int quarter) {
        FloatingPopulationResponse result = new FloatingPopulationResponse();

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

    protected WorkplacePopulationResponse sumWorkplacePopulation(List<WorkplacePopulation> populations) {
        WorkplacePopulationResponse result = new WorkplacePopulationResponse();

        result.setTotal_num_of_workplace(populations.stream().mapToInt(WorkplacePopulation::getTotal_num_of_workplace).sum());

        result.setNum_of10age_workplace(populations.stream().mapToInt(WorkplacePopulation::getNum_of10age_workplace).sum());
        result.setNum_of20age_workplace(populations.stream().mapToInt(WorkplacePopulation::getNum_of20age_workplace).sum());
        result.setNum_of30age_workplace(populations.stream().mapToInt(WorkplacePopulation::getNum_of30age_workplace).sum());
        result.setNum_of40age_workplace(populations.stream().mapToInt(WorkplacePopulation::getNum_of40age_workplace).sum());
        result.setNum_of50age_workplace(populations.stream().mapToInt(WorkplacePopulation::getNum_of50age_workplace).sum());
        result.setNum_of60age_workplace(populations.stream().mapToInt(WorkplacePopulation::getNum_of60age_workplace).sum());

        result.setNum_of_men_workplace(populations.stream().mapToInt(WorkplacePopulation::getNum_of_men_workplace).sum());
        result.setNum_of10men_workplace(populations.stream().mapToInt(WorkplacePopulation::getNum_of10men_workplace).sum());
        result.setNum_of20men_workplace(populations.stream().mapToInt(WorkplacePopulation::getNum_of20men_workplace).sum());
        result.setNum_of30men_workplace(populations.stream().mapToInt(WorkplacePopulation::getNum_of30men_workplace).sum());
        result.setNum_of40men_workplace(populations.stream().mapToInt(WorkplacePopulation::getNum_of40men_workplace).sum());
        result.setNum_of50men_workplace(populations.stream().mapToInt(WorkplacePopulation::getNum_of50men_workplace).sum());
        result.setNum_of60men_workplace(populations.stream().mapToInt(WorkplacePopulation::getNum_of60men_workplace).sum());

        result.setNum_of_women_workplace(populations.stream().mapToInt(WorkplacePopulation::getNum_of_women_workplace).sum());
        result.setNum_of10women_workplace(populations.stream().mapToInt(WorkplacePopulation::getNum_of10women_workplace).sum());
        result.setNum_of20women_workplace(populations.stream().mapToInt(WorkplacePopulation::getNum_of20women_workplace).sum());
        result.setNum_of30women_workplace(populations.stream().mapToInt(WorkplacePopulation::getNum_of30women_workplace).sum());
        result.setNum_of40women_workplace(populations.stream().mapToInt(WorkplacePopulation::getNum_of40women_workplace).sum());
        result.setNum_of50women_workplace(populations.stream().mapToInt(WorkplacePopulation::getNum_of50women_workplace).sum());
        result.setNum_of60women_workplace(populations.stream().mapToInt(WorkplacePopulation::getNum_of60women_workplace).sum());

        return result;
    }

    protected ResidentPopulationResponse sumResidentPopulation(List<ResidentPopulation> populations) {
        ResidentPopulationResponse result = new ResidentPopulationResponse();

        result.setTotal_num_of_residents(populations.stream().mapToInt(ResidentPopulation::getTotal_num_of_residents).sum());

        result.setNum_of_age10residents(populations.stream().mapToInt(ResidentPopulation::getNum_of_age10residents).sum());
        result.setNum_of_age20residents(populations.stream().mapToInt(ResidentPopulation::getNum_of_age20residents).sum());
        result.setNum_of_age30residents(populations.stream().mapToInt(ResidentPopulation::getNum_of_age30residents).sum());
        result.setNum_of_age40residents(populations.stream().mapToInt(ResidentPopulation::getNum_of_age40residents).sum());
        result.setNum_of_age50residents(populations.stream().mapToInt(ResidentPopulation::getNum_of_age50residents).sum());
        result.setNum_of_age60residents(populations.stream().mapToInt(ResidentPopulation::getNum_of_age60residents).sum());

        result.setNum_of_men_residents(populations.stream().mapToInt(ResidentPopulation::getNum_of_men_residents).sum());
        result.setNum_of_men10residents(populations.stream().mapToInt(ResidentPopulation::getNum_of_men10residents).sum());
        result.setNum_of_men20residents(populations.stream().mapToInt(ResidentPopulation::getNum_of_men20residents).sum());
        result.setNum_of_men30residents(populations.stream().mapToInt(ResidentPopulation::getNum_of_men30residents).sum());
        result.setNum_of_men40residents(populations.stream().mapToInt(ResidentPopulation::getNum_of_men40residents).sum());
        result.setNum_of_men50residents(populations.stream().mapToInt(ResidentPopulation::getNum_of_men50residents).sum());
        result.setNum_of_men60residents(populations.stream().mapToInt(ResidentPopulation::getNum_of_men60residents).sum());

        result.setNum_of_women_residents(populations.stream().mapToInt(ResidentPopulation::getNum_of_women_residents).sum());
        result.setNum_of_women10residents(populations.stream().mapToInt(ResidentPopulation::getNum_of_women10residents).sum());
        result.setNum_of_women20residents(populations.stream().mapToInt(ResidentPopulation::getNum_of_women20residents).sum());
        result.setNum_of_women30residents(populations.stream().mapToInt(ResidentPopulation::getNum_of_women30residents).sum());
        result.setNum_of_women40residents(populations.stream().mapToInt(ResidentPopulation::getNum_of_women40residents).sum());
        result.setNum_of_women50residents(populations.stream().mapToInt(ResidentPopulation::getNum_of_women50residents).sum());
        result.setNum_of_women60residents(populations.stream().mapToInt(ResidentPopulation::getNum_of_women60residents).sum());

        return result;
    }

    protected IndustryResponse sumIndustry(List<IndustryAnalysis> industries, int y, int q) {
        IndustryResponse result = new IndustryResponse(y, q);

        result.setNum_of_stores(industries.stream().mapToInt(IndustryAnalysis::getNum_of_stores).sum());
        result.setNum_of_franchise_stores(industries.stream().mapToInt(IndustryAnalysis::getNum_of_franchise_stores).sum());
        result.setTotal_num_of_stores(industries.stream().mapToInt(IndustryAnalysis::getTotal_num_of_stores).sum());
        result.setNum_of_close_stores(industries.stream().mapToInt(IndustryAnalysis::getNum_of_close_stores).sum());
        result.setNum_of_open_stores(industries.stream().mapToInt(IndustryAnalysis::getNum_of_open_stores).sum());

        return result;
    }

    protected SalesResponse sumSales(List<Sales> sales, int y, int q) {
        SalesResponse result = new SalesResponse(y, q);

        result.setSales_per_quarter(sales.stream().mapToLong(Sales::getSales_per_quarter).sum());
        result.setNum_of_stores(sales.stream().mapToInt(Sales::getNum_of_stores).sum());

        result.setMon_sales(sales.stream().mapToLong(Sales::getMon_sales).sum());
        result.setTue_sales(sales.stream().mapToLong(Sales::getTue_sales).sum());
        result.setWed_sales(sales.stream().mapToLong(Sales::getWed_sales).sum());
        result.setThu_sales(sales.stream().mapToLong(Sales::getThu_sales).sum());
        result.setFri_sales(sales.stream().mapToLong(Sales::getFri_sales).sum());
        result.setSat_sales(sales.stream().mapToLong(Sales::getSat_sales).sum());
        result.setSun_sales(sales.stream().mapToLong(Sales::getSun_sales).sum());

        result.setWeekly_sales(sales.stream().mapToLong(Sales::getWeekly_sales).sum());
        result.setWeekend_sales(sales.stream().mapToLong(Sales::getWeekend_sales).sum());

        result.setTimeFrom00To06(sales.stream().mapToLong(Sales::getTimeFrom00To06).sum());
        result.setTimeFrom06To11(sales.stream().mapToLong(Sales::getTimeFrom06To11).sum());
        result.setTimeFrom11To14(sales.stream().mapToLong(Sales::getTimeFrom11To14).sum());
        result.setTimeFrom14To17(sales.stream().mapToLong(Sales::getTimeFrom14To17).sum());
        result.setTimeFrom17To21(sales.stream().mapToLong(Sales::getTimeFrom17To21).sum());
        result.setTimeFrom21To24(sales.stream().mapToLong(Sales::getTimeFrom21To24).sum());

        result.setMale_sales(sales.stream().mapToLong(Sales::getMale_sales).sum());
        result.setFemale_sales(sales.stream().mapToLong(Sales::getFemale_sales).sum());

        result.setAge10Sales(sales.stream().mapToLong(Sales::getAge10Sales).sum());
        result.setAge20Sales(sales.stream().mapToLong(Sales::getAge20Sales).sum());
        result.setAge30Sales(sales.stream().mapToLong(Sales::getAge30Sales).sum());
        result.setAge40Sales(sales.stream().mapToLong(Sales::getAge40Sales).sum());
        result.setAge50Sales(sales.stream().mapToLong(Sales::getAge50Sales).sum());
        result.setAge60Sales(sales.stream().mapToLong(Sales::getAge60Sales).sum());

        return result;
    }

    @Data
    @AllArgsConstructor
    static class RentalFeeResponse {
        private int year;
        private int quarter;

        private Integer rentalfee_total;
        private Integer rentalfee_1st_floor;
        private Integer rentalfee_except_1st_floor;
    }

    @Data
    static class TotalData<T> {

        private T basicData;
        private Collection<T> industry = new ArrayList<>();
        private T avgPeriod;
        private Collection<T> sales = new ArrayList<>();
        private T commerceChange;
        private T incomeConsumption;
        private Collection<T> population = new ArrayList<>();
        private T facility;

    }

    @Data
    @AllArgsConstructor
    static class BasicData {
        private String areaName;
        private int year;
        private int quarter;
        private String serviceName;
    }

    @Data
    @AllArgsConstructor
    static class AvgOperationPeriodResponse {
        private Float avg_period;
    }

    @Data
    static class FacilityResponse {
        private Integer num_of_accommodation;
        private Integer num_of_airport;
        private Integer num_of_bank;
        private Integer num_of_bus_stop;
        private Integer num_of_bus_terminal;
        private Integer num_of_department_store;
        private Integer num_of_elementary_school;
        private Integer num_of_facility;
        private Integer num_of_general_hospital;
        private Integer num_of_government_office;
        private Integer num_of_high_school;
        private Integer num_of_hospital;
        private Integer num_of_kindergarten;
        private Integer num_of_middle_school;
        private Integer num_of_pharmacy;
        private Integer num_of_rail_station;
        private Integer num_of_subway;
        private Integer num_of_supermarket;
        private Integer num_of_theater;
        private Integer num_of_university;
    }

    @Data
    static class FloatingPopulationResponse {
        private Integer year;
        private Integer quarter;
        private Integer total_flpop;
        private Integer male_flpop;
        private Integer female_flpop;

        private Integer age_10_flpop;
        private Integer age_20_flpop;
        private Integer age_30_flpop;
        private Integer age_40_flpop;
        private Integer age_50_flpop;
        private Integer age_60_flpop;

        private Integer time_1_flpop;
        private Integer time_2_flpop;
        private Integer time_3_flpop;
        private Integer time_4_flpop;
        private Integer time_5_flpop;
        private Integer time_6_flpop;

        private Integer mon_flpop;
        private Integer tue_flpop;
        private Integer wed_flpop;
        private Integer thu_flpop;
        private Integer fri_flpop;
        private Integer sat_flpop;
        private Integer sun_flpop;
    }

    @Data
    static class WorkplacePopulationResponse {
        private Integer total_num_of_workplace;

        private Integer num_of10age_workplace;
        private Integer num_of20age_workplace;
        private Integer num_of30age_workplace;
        private Integer num_of40age_workplace;
        private Integer num_of50age_workplace;
        private Integer num_of60age_workplace;

        private Integer num_of_men_workplace;
        private Integer num_of10men_workplace;
        private Integer num_of20men_workplace;
        private Integer num_of30men_workplace;
        private Integer num_of40men_workplace;
        private Integer num_of50men_workplace;
        private Integer num_of60men_workplace;

        private Integer num_of_women_workplace;
        private Integer num_of10women_workplace;
        private Integer num_of20women_workplace;
        private Integer num_of30women_workplace;
        private Integer num_of40women_workplace;
        private Integer num_of50women_workplace;
        private Integer num_of60women_workplace;
    }

    @Data
    static class ResidentPopulationResponse {
        private Integer total_num_of_residents;

        private Integer num_of_age10residents;
        private Integer num_of_age20residents;
        private Integer num_of_age30residents;
        private Integer num_of_age40residents;
        private Integer num_of_age50residents;
        private Integer num_of_age60residents;

        private Integer num_of_men_residents;
        private Integer num_of_men10residents;
        private Integer num_of_men20residents;
        private Integer num_of_men30residents;
        private Integer num_of_men40residents;
        private Integer num_of_men50residents;
        private Integer num_of_men60residents;

        private Integer num_of_women_residents;
        private Integer num_of_women10residents;
        private Integer num_of_women20residents;
        private Integer num_of_women30residents;
        private Integer num_of_women40residents;
        private Integer num_of_women50residents;
        private Integer num_of_women60residents;
    }

    @Data
    static class IndustryResponse {
        private Integer year;
        private Integer quarter;
        private Integer num_of_stores;
        private Integer num_of_franchise_stores;
        private Integer total_num_of_stores;
        private Integer num_of_close_stores;
        private Integer num_of_open_stores;

        IndustryResponse(int year, int quarter) {
            this.year = year;
            this.quarter = quarter;
        }
    }

    @Data
    @AllArgsConstructor
    static class IncomeConsumptionResponse {
        private Long average_monthly_income;
        private Long total_amount_spent;
    }

    @Data
    static class SalesResponse {
        private int year;
        private int quarter;
        private Long sales_per_quarter;    //분기당 매출 금액
        private int num_of_stores;

        //요일별 매출 금액
        private Long mon_sales;
        private Long tue_sales;
        private Long wed_sales;
        private Long thu_sales;
        private Long fri_sales;
        private Long sat_sales;
        private Long sun_sales;

        private Long weekly_sales;
        private Long weekend_sales;

        //시간대별 매출 금액
        private Long timeFrom00To06;
        private Long timeFrom06To11;
        private Long timeFrom11To14;
        private Long timeFrom14To17;
        private Long timeFrom17To21;
        private Long timeFrom21To24;

        //성별 매출 금액
        private Long male_sales;
        private Long female_sales;

        //연령대별 매출
        private Long age10Sales;
        private Long age20Sales;
        private Long age30Sales;
        private Long age40Sales;
        private Long age50Sales;
        private Long age60Sales;

        SalesResponse(int year, int quarter) {
            this.year = year;
            this.quarter = quarter;
        }
    }

}
