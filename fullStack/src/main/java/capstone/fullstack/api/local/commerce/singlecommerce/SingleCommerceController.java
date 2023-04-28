package capstone.fullstack.api.local.commerce.singlecommerce;

import capstone.fullstack.domain.Facility;
import capstone.fullstack.domain.IncomeConsumption;
import capstone.fullstack.domain.Industry;
import capstone.fullstack.domain.Sales;
import capstone.fullstack.domain.population.Resident;
import capstone.fullstack.domain.population.Workplace;
import capstone.fullstack.repository.local.commerce.FacilityRepository;
import capstone.fullstack.repository.local.commerce.IncomeConsumptionRepository;
import capstone.fullstack.repository.local.commerce.IndustryRepository;
import capstone.fullstack.repository.local.commerce.SalesRepository;
import capstone.fullstack.repository.local.commerce.population.ResidentRepository;
import capstone.fullstack.repository.local.commerce.population.WorkplaceRepository;
import capstone.fullstack.resultvo.FacilityVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/single_commerce")
public class SingleCommerceController {

    private final ResidentRepository residentRepository;
    private final WorkplaceRepository workplaceRepository;
    private final FacilityRepository facilityRepository;
    private final IncomeConsumptionRepository incomeConsumptionRepository;
    private final IndustryRepository industryRepository;
    private final SalesRepository salesRepository;

//    facilityVOList = facilityVOList.stream().
//    sorted(Comparator.comparing(FacilityVO::getYear).reversed().thenComparing(FacilityVO::getQuarter)).collect(Collectors.toList());

    /**
     * 해당 상권의 상주인구 정보 반환해준다.
     */
    @GetMapping("/resident")
    public List<Resident> findAllResident(@RequestParam Integer commercialCode) {
        //해당 상권 상주인구 정보 반환해줌
        List<Resident> residentList = residentRepository.findByCommercialCode(commercialCode);
        residentList = residentList.stream()
                .sorted(Comparator.comparing(Resident::getYear).reversed()
                        .thenComparing(Resident::getQuarter))
                .collect(Collectors.toList());
        //년도 내림차순, 분기 오름차순
        return residentList;
    }

    /**
     * 해당 상권의 직장인구 정보를 반환해준다.
     */
    @GetMapping("/workplace")
    public List<Workplace> findAllWorkplace(@RequestParam Integer commercialCode) {
        //해당 상권 직장인구
        List<Workplace> workplaceList = workplaceRepository.findByCommercialCode(commercialCode);
        workplaceList = workplaceList.stream().sorted(Comparator.comparing(Workplace::getYear).reversed().thenComparing(Workplace::getQuarter)).collect(Collectors.toList());

        return workplaceList;
    }

    /**
     * 해당 상권의 집객시설 정보를 반환해준다.
     */
    @GetMapping("/facility")
    public List<Facility> findAllFacility(@RequestParam Integer commercialCode) {
        List<Facility> facilityList = facilityRepository.findByCommercialCode(commercialCode);
        facilityList = facilityList.stream()
                .sorted(Comparator.comparing(Facility::getYear).reversed()
                        .thenComparing(Facility::getQuarter))
                .collect(Collectors.toList());

        return facilityList;
    }

    /**
     * 해당 상권의 소득소비 정보를 반환해준다.
     */
    @GetMapping("/income_consumption")
    public List<IncomeConsumption> findAllIC(@RequestParam Integer commercialCode) {
        List<IncomeConsumption> resList = incomeConsumptionRepository.findByCommercialCode(commercialCode);
        resList = resList.stream()
                .sorted(Comparator.comparing(IncomeConsumption::getYear).reversed()
                        .thenComparing(IncomeConsumption::getQuarter))
                .collect(Collectors.toList());

        return resList;
    }

    /**
     * 해당 상권의 업종 분석 정보를 반환한다.
     */
    @GetMapping("/industry")
    public List<Industry> findAllIndustry(@RequestParam Integer commercialCode, @RequestParam String serviceName) {
        List<Industry> resList = industryRepository.findByCommercialCodeAndServiceName(commercialCode, serviceName);
        resList = resList.stream().
                sorted(Comparator.comparing(Industry::getYear).reversed()
                        .thenComparing(Industry::getQuarter))
                .collect(Collectors.toList());

        return resList;
    }

    /**
     * 해당 상권의 매출 분석 정보를 반환한다.
     */
    @GetMapping("/sales")
    public List<Sales> findAllSales(@RequestParam Integer commercialCode, @RequestParam String serviceName) {
        List<Sales> salesList = salesRepository.findByCommercialCodeAndServiceName(commercialCode, serviceName);
        salesList = salesList.stream()
                .sorted(Comparator.comparing(Sales::getYear).reversed()
                        .thenComparing(Sales::getQuarter))
                .collect(Collectors.toList());

        return salesList;
    }
}
