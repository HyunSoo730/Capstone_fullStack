package capstone.fullstack.api.local.commerce.dong;

import capstone.fullstack.domain.Local;
import capstone.fullstack.repository.local.commerce.IndustryRepository;
import capstone.fullstack.repository.local.commerce.LocalRepository;
import capstone.fullstack.resultvo.*;
import capstone.fullstack.service.local.commerce.*;
import capstone.fullstack.service.local.commerce.all.AllService;
import capstone.fullstack.service.local.commerce.population.ResidentService;
import capstone.fullstack.service.local.commerce.population.WorkplaceService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/local-commerce/integrated_dong")
public class AllOfDongController {

    private final SalesService salesService;
    private final IndustryService industryService;
    private final CommerceChangeService commerceChangeService;
    private final WorkplaceService workplaceService;
    private final ResidentService residentService;
    private final IncomeConsumptionService incomeConsumptionService;
    private final FacilityService facilityService;
    private final LocalService localService;
    private final LocalRepository localRepository;

    private final AllService allService;

    private final IndustryRepository industryRepository;

    /**
     * 행정동 통합해서 반환하기. (삼성동으로 들어오면 삼성1동, 삼성2동 정보 합쳐서 반환
     * 평균을 내야하는 것들 역시 따로 구해서 반환해줄 것.
     */

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class RequestDTO {
        private String borough;
        private String dong;
        private String serviceName;
    }

    @Data
    static class Request2DTO {
        private String borough;
        private String dong;
    }


    /**
     * 업종 분석 엔티티 반환.
     */
    @PostMapping("/industry")
    public List<IndustryVO> findAllIndustry(@RequestBody RequestDTO requestDTO) {
        String findDong = requestDTO.getDong().substring(0, 2);  //삼성동 -> 삼성만 추출
//        log.info("findDong : {}", findDong);
        List<Local> locals = localRepository.findByBoroughAndDongStartingWith(requestDTO.getBorough(), findDong);
//        List<String> dongs = locals.stream().map(local -> local.getDong()).distinct().collect(Collectors.toList());
//        log.info("dongs : {}", dongs);
        List<Integer> codes = locals.stream().map(local -> local.getCommercialCode()).distinct().collect(Collectors.toList());
//        log.info("codes: {}", codes);


        //통합 행정동의 모든 상권코드 찾았음.
        List<IndustryVO> res = industryService.getIndustryListByAllDong(codes, requestDTO.getServiceName(), requestDTO.getDong());
        return res;
    }

    /**
     * 상주인구 반환.
     */
    @PostMapping("/resident")
    public List<ResidentVO> findAllResident(@RequestBody Request2DTO dto) {
        String findDong = dto.getDong().substring(0, 2);   //통합 정보 꺼낸 후.
        List<Local> locals = localRepository.findByBoroughAndDongStartingWith(dto.getBorough(), findDong);
        List<Integer> codes = locals.stream().map(local -> local.getCommercialCode()).distinct().collect(Collectors.toList());

        //통합 행정동의 모든 상권코드 찾았으니. 해당 정보에 대한 상주인구.
        List<ResidentVO> res = residentService.getResidentListByAllDong(codes, dto.getDong());
        return res;
    }

    /**
     * 직장인구 반환.
     */
    @PostMapping("/workplace")
    public List<WorkplaceVO> findAllWorkplace(@RequestBody Request2DTO dto) {
        String findDong = dto.getDong().substring(0, 2);   //통합 정보 꺼낸 후.
        List<Local> locals = localRepository.findByBoroughAndDongStartingWith(dto.getBorough(), findDong);
        List<Integer> codes = locals.stream().map(local -> local.getCommercialCode()).distinct().collect(Collectors.toList());

        //통합 행정동에 대한 직장인구 반환
        List<WorkplaceVO> res = workplaceService.getWorkplaceListByAllDong(codes, dto.getDong());
        return res;
    }

    /**
     * 상권변화 엔티티 반환
     */
    @PostMapping("/change")
    public HashMap<String, List<CommerceChangeVO>> findAllCC(@RequestBody Request2DTO dto) {
        String findDong = dto.getDong().substring(0, 2);   //통합 정보 꺼낸 후.
        List<Local> locals = localRepository.findByBoroughAndDongStartingWith(dto.getBorough(), findDong);
        List<String> dongs = locals.stream().map(local -> local.getDong()).distinct().collect(Collectors.toList());

//        log.info("dongs : {}", dongs);
        //이제 원하는 자치구에 속해있는 동을 찾았기에 해당 동들의 상권변화들 반환
        HashMap<String, List<CommerceChangeVO>> resMap = new HashMap<>();
        for (String dong : dongs) {
            List<CommerceChangeVO> cc = commerceChangeService.findAlletrics(dong);
            resMap.put(dong, cc);
        }
//        List<CommerceChangeVO> res = commerceChangeService.findAlletrics(findDong);
//        return res;
        return resMap;
    }

    /**
     * 집객시설 엔티티 반환
     */
    @PostMapping("/facility")
    public List<FacilityVO> findAllFacility(@RequestBody Request2DTO dto) {
        String findDong = dto.getDong().substring(0, 2);   //통합 정보 꺼낸 후.
        List<Local> locals = localRepository.findByBoroughAndDongStartingWith(dto.getBorough(), findDong);
        List<Integer> codes = locals.stream().map(local -> local.getCommercialCode()).distinct().collect(Collectors.toList());

        List<FacilityVO> res = facilityService.getFacilityByAllDong(codes, dto.getDong());
        return res;
    }

    /**
     * 소득소비 엔티티 반환
     */
    @PostMapping("/income_consumption")
    public List<IncomeConsumptionVO> findAllIC(@RequestBody Request2DTO dto) {
        String findDong = dto.getDong().substring(0, 2);   //통합 정보 꺼낸 후.
        List<Local> locals = localRepository.findByBoroughAndDongStartingWith(dto.getBorough(), findDong);
        List<Integer> codes = locals.stream().map(local -> local.getCommercialCode()).distinct().collect(Collectors.toList());

        List<IncomeConsumptionVO> res = incomeConsumptionService.getIncomeConsumptionByAllDong(codes, dto.getDong());
        return res;
    }


    /**
     * 매출분석 엔티티 반환
     */
    @PostMapping("/sales")
    public List<SalesVO> findAllSales(@RequestBody RequestDTO dto) {
        String findDong = dto.getDong().substring(0, 2);   //통합 정보 꺼낸 후.
        List<Local> locals = localRepository.findByBoroughAndDongStartingWith(dto.getBorough(), findDong);
        List<Integer> codes = locals.stream().map(local -> local.getCommercialCode()).distinct().collect(Collectors.toList());

        List<SalesVO> res = salesService.getSalesListByAllDong(codes, dto.getServiceName(), dto.getDong());
        return res;
    }
}
