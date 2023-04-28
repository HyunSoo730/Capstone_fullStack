package capstone.fullstack.api.local.commerce.dong;

import capstone.fullstack.repository.local.commerce.IndustryRepository;
import capstone.fullstack.resultvo.*;
import capstone.fullstack.service.local.commerce.*;
import capstone.fullstack.service.local.commerce.all.AllService;
import capstone.fullstack.service.local.commerce.population.ResidentService;
import capstone.fullstack.service.local.commerce.population.WorkplaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class CommerceController {

    private final SalesService salesService;
    private final IndustryService industryService;
    private final CommerceChangeService commerceChangeService;
    private final WorkplaceService workplaceService;
    private final ResidentService residentService;
    private final IncomeConsumptionService incomeConsumptionService;
    private final FacilityService facilityService;
    private final LocalService localService;

    private final AllService allService;

    private final IndustryRepository industryRepository;

//    @PostMapping("/api/v1/target")
//    public Sales findDong(@RequestParam("SalesId") String id) {
//        Sales sales = salesService.find(Long.parseLong(id));
//        return sales;
//    }
//
//    @GetMapping("/api/v2/target")
//    public Industry find(@RequestParam("industryId") Long id) {
//        Industry industry = industryService.find(id);
//        return industry;
//    }
//
//    @GetMapping("/api/v3/target")
//    public CommerceChange findCC(@RequestParam("id") Long id) {
//        return commerceChangeService.find(id);
//    }
//
//    @GetMapping("/api/v4/target")
//    public Workplace findWorkplace(@RequestParam Long id) {
//        return workplaceService.findWorkplacePopulation(id);
//    }
//
//    @GetMapping("/api/v5/target")
//    public Resident findResident(@RequestParam Long id) {
//        return residentService.findResident(id);
//    }
//
//    @GetMapping("/api/v6/target")
//    public IncomeConsumption findIncomeConsumption(@RequestParam Long id) {
//        return incomeConsumptionService.findIncome(id);
//    }
//
//    @GetMapping("/api/v7/target")
//    public Facility findFacility(@RequestParam Long id) {
//        return facilityService.findFacility(id);
//    }
//
//    //지금까지의 데이터... 행정동 -> 상권코드 얻어와서 상권코드로 데이터들을 조회해야함
//    @GetMapping("/api/find/dongtocode")
//    public List<Local> findAllLocal(@RequestParam String dong) {
//        List<Local> allLocalWithDong = localService.findAllLocalWithDong(dong);
//        return allLocalWithDong;
//    }

    /**
     * 효율성 -> join을 통해 필요한 컬럼들 한번에 객체화 해서 반환. --> 컬럼이 겹치는게 몇개 없어 조인은 힘듬 결국 모두 객체화해서 꺼내기
     * Query로 직접 짜던가 커스텀 방식으로 따로 사용
     * 포트 열어놈. putty -> 포트 열어둠.
     */

    //특정 행정동의 특정 서비스의 업종 분석.

    /**
     * 업종 분석 엔티티 반환. 근데 행정동으로 반환이 안되서 일단 있는 그대로 반환.
     */
    @GetMapping("/api/local-commerce/industry")   //업종 분석 엔티티. 행정동, 서비스 업종 명 파라미터로 받음.
    public List<IndustryVO> findAllIndustryEntity(@RequestParam String dong, @RequestParam String serviceName) {
        //행정동과 서비스 업종이 들어오면.

        //행정동을 request로 받으면
        //1. 상권코드로 변환
        List<Integer> allCommercialCode = allService.findAllCommercialCode(dong);
        //2. 받아온 상권 코드로 엔티티 생성
        //상권코드와 같은 엔티티 리스트 형식으로 다 반환.
        // 먼저 업종 분석 엔티티부터 받아오기
        List<IndustryVO> res = industryService.getIndustryListByDong(allCommercialCode, serviceName);
        return res;
        //해결
    }

    /**
     * 상권 변화 엔티티 반환. 8개년치 특정 년도, 특정 분기, 해당 행정동의 상권 변화지표
     */
    @GetMapping("/api/local-commerce/change")   //상권 변화 테이블. 행정동 파라미터로 받아서.
    public List<CommerceChangeVO> findAllCommerceChange(@RequestParam String dong) {
        List<CommerceChangeVO> res = commerceChangeService.findMetrics(dong);
        return res;
        //해결
    }

    /**
     * 매출 분석 엔티티 반환.
     */
    @GetMapping("/api/local-commerce/sales")
    public List<SalesVO> findAllSales(@RequestParam String dong, @RequestParam String serviceName) {
        //행정동과 서비스 업종 명 들어오면 매출 분석 엔티티 반환
        //1. 특정 행정동을 상권코드로
        List<Integer> allCommercialCode = allService.findAllCommercialCode(dong);
        //받아온 상권코드로 엔티티 만들어야함

        List<SalesVO> res = salesService.getSalesListByDong(allCommercialCode, serviceName);

        return res;
        //해결
    }

//    private final LocalRepository localRepository;
//    @GetMapping("/api/test")
//    public String temp(@RequestParam Integer code) {
//        Local local = localRepository.findFirstByCommercialCode(code);
//        return local.getDong();
//    }

    /**
     * 상주인구 엔티티 반환.
     */
    @GetMapping("/api/local-commerce/resident")
    public List<ResidentVO> findAllResident(@RequestParam String dong) {
        //1. 특정 행정동을 상권코드로
        List<Integer> allCommercialCode = allService.findAllCommercialCode(dong);
        //받아온 상궈코드로 엔티티
        List<ResidentVO> res = residentService.getResidentListByDong(allCommercialCode);
        return res;
        //해결
    }

    /**
     * 직장인구 엔티티 반환
     */
    @GetMapping("/api/local-commerce/workplace")
    public List<WorkplaceVO> findAllWorkplace(@RequestParam String dong) {
        //1. 특정 행정동 상궈코드로
        List<Integer> allCommercialCode = allService.findAllCommercialCode(dong);
        //받아온 상권코드로 엔티티
        List<WorkplaceVO> res = workplaceService.getWorkplaceListByDong(allCommercialCode);
        return res;
        //해결. 연도 -> 분기 -> 해당 행정동에 대한 누계
    }

    /**
     * 소득소비 엔티티 반환 // 업종과는 관련 x
     * 서울시 평균 소득 금액은 프론트에서 따로 기입
     */
    @GetMapping("/api/local-commerce/income_consumption")
    public List<IncomeConsumptionVO> findAllIC(@RequestParam String dong) {
        //1. 특정 행정동 상권코드로
        List<Integer> allCommercialCode = allService.findAllCommercialCode(dong);
        //2. 해당 상권코드들로 엔티티
        List<IncomeConsumptionVO> res = incomeConsumptionService.getIncomeConsumptionByDong(allCommercialCode);
        return res;
        //해결
    }

    /**
     * 집객시설 엔티티 반환
     * 특정 년도, 특정 분기의 해당 상권 코드에 해당 얘기
     */
    @GetMapping("/api/local-commerce/facility")
    public List<FacilityVO> findAllFacility(@RequestParam String dong) {
        //1. 행정동 -> 상권 코드
        List<Integer> allCommercialCode = allService.findAllCommercialCode(dong);
        List<FacilityVO> res = facilityService.getFacilityByDong(allCommercialCode);
        return res;
        //해결
    }

//    @GetMapping("/test")
//    public HashMap<String, Integer> test(@RequestParam String dong) {
//        HashMap<String, Integer> r = new HashMap<>();
//        r.put(dong, 1);
//        return r;
//    }

}
