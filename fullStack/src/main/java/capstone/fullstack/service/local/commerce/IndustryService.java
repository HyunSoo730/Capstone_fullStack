package capstone.fullstack.service.local.commerce;

import capstone.fullstack.domain.Industry;
import capstone.fullstack.domain.Local;
import capstone.fullstack.repository.local.commerce.IndustryRepository;
import capstone.fullstack.repository.local.commerce.LocalRepository;
import capstone.fullstack.resultvo.IndustryVO;
import capstone.fullstack.service.local.commerce.all.AllService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
/**
 * 업종 분석 서비스
 */
public class IndustryService {

    private final IndustryRepository industryRepository;
    private final AllService allService;
    private final LocalRepository localRepository;

    public Industry find(Long id) {
        Optional<Industry> findIndustry = industryRepository.findById(id);
        return findIndustry.get();
    }

    /**
     * 특정 년도, 특정 분기, 서비스 이름으로 업종 분석 엔티티 반환
     */

    public List<IndustryVO> getIndustryListByDong(List<Integer> commercialCodeList, String sn) {
        List<Industry> industryList = industryRepository.findByCommercialCodeInAndServiceNameEquals(commercialCodeList, sn);

        Map<String, IndustryVO> industryVOMap = new HashMap<>();

        for (Industry industry : industryList) {
            String serviceName = industry.getServiceName();
            int numOfStores = industry.getNumOfStores();
            int totalNumOfStores = industry.getTotalNumOfStores();
            int numOfOpenStores = industry.getNumOfOpenStores();
            int numOfCloseStores = industry.getNumOfCloseStores();
            int numOfFranchiseStores = industry.getNumOfFranchiseStores();
//            log.info("잠시 확인 : {}", industry.toString());
            // 행정동으로 변환
            Local findLocal = localRepository.findFirstByCommercialCode(industry.getCommercialCode());
//            log.info("findLocal : {}", findLocal.toString());

            String convertedDong = findLocal.getDong();  //상권코드로 -> 행정동

            // 이미 존재하는 IndustryVO인 경우 누적
            // year, quarter 별로 누적된 IndustryVO 객체를 만들고, Map에 저장
            String key = String.valueOf(industry.getYear()) + String.valueOf(industry.getQuarter());
            IndustryVO industryVO = industryVOMap.computeIfAbsent(key, k -> {
                IndustryVO newIndustryVO = new IndustryVO();
                newIndustryVO.setYear(industry.getYear());
                newIndustryVO.setQuarter(industry.getQuarter());
                newIndustryVO.setDong(convertedDong);
                newIndustryVO.setServiceName(serviceName);

                return newIndustryVO;
            });

            // 누적값 계산
            industryVO.setNumOfStores(industryVO.getNumOfStores() + numOfStores);
            industryVO.setTotalNumOfStores(industryVO.getTotalNumOfStores() + totalNumOfStores);
            industryVO.setNumOfOpenStores(industryVO.getNumOfOpenStores() + numOfOpenStores);
            industryVO.setNumOfCloseStores(industryVO.getNumOfCloseStores() + numOfCloseStores);
            industryVO.setNumOfFranchiseStores(industryVO.getNumOfFranchiseStores() + numOfFranchiseStores);
        }

        // Map에서 IndustryVO 리스트로 변환
        List<IndustryVO> industryVOList = new ArrayList<>(industryVOMap.values());
        industryVOList = industryVOList.stream().sorted(Comparator.comparing(IndustryVO::getYear).reversed().thenComparing(IndustryVO::getQuarter)).collect(Collectors.toList());

        return industryVOList;
    }

}
