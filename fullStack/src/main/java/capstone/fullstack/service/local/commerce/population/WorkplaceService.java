package capstone.fullstack.service.local.commerce.population;

import capstone.fullstack.domain.Local;
import capstone.fullstack.domain.population.Workplace;
import capstone.fullstack.repository.local.commerce.LocalRepository;
import capstone.fullstack.repository.local.commerce.population.WorkplaceRepository;
import capstone.fullstack.resultvo.WorkplaceVO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class WorkplaceService {

    private final WorkplaceRepository workplaceRepository;
    private final LocalRepository localRepository;

    public Workplace findWorkplacePopulation(Long id) {
        return workplaceRepository.findById(id).get();
    }


    /**
     * 특정 년도, 특정 분기에 해당하는 상권코드 -> 행정동 변환해서 해당 행정동에 대한 누계치 엔티티로
     */
    public List<WorkplaceVO> getWorkplaceListByDong(List<Integer> allCommercialCode) {

        List<Workplace> workplaceList = workplaceRepository.findByCommercialCodeIn(allCommercialCode);

        Map<String, WorkplaceVO> workplaceMap = new HashMap<>(); //중복 제거

        for (Workplace workplace : workplaceList) {
            Local findLocal = localRepository.findFirstByCommercialCode(workplace.getCommercialCode());
            String convertedDong = findLocal.getDong();  //상권코드 -> 행정동

            //이미 존재하는 workplaceVO 경우 누적
            //year, quarter 별로 누적
            String key = String.valueOf(workplace.getYear()) + String.valueOf(workplace.getQuarter());
            WorkplaceVO workplaceVO = workplaceMap.computeIfAbsent(key, k -> {
                WorkplaceVO newWorkplaceVO = new WorkplaceVO();
                newWorkplaceVO.setYear(workplace.getYear());
                newWorkplaceVO.setQuarter(workplace.getQuarter());
                newWorkplaceVO.setDong(convertedDong);
                //미리 넣어야 하는건 미리 넣고 시작 : 특정 년도 -> 특정 분기를 기준으로 (해당 행정동)의 정보들
                return newWorkplaceVO;
            });

            //누적값 계산
            workplaceVO.setTotalNumOfWorkplace(workplaceVO.getTotalNumOfWorkplace() + workplace.getTotalNumOfWorkplace());
            workplaceVO.setNumOfMenWorkplace(workplaceVO.getNumOfMenWorkplace() + workplace.getNumOfMenWorkplace());
            workplaceVO.setNumOfWomenWorkplace(workplaceVO.getNumOfWomenWorkplace() + workplace.getNumOfWomenWorkplace());
            //연령대별 직장 인구 수
            workplaceVO.setNumOf10AgeWorkplace(workplaceVO.getNumOf10AgeWorkplace() + workplace.getNumOf10AgeWorkplace());
            workplaceVO.setNumOf20AgeWorkplace(workplaceVO.getNumOf20AgeWorkplace() + workplace.getNumOf20AgeWorkplace());
            workplaceVO.setNumOf30AgeWorkplace(workplaceVO.getNumOf30AgeWorkplace() + workplace.getNumOf30AgeWorkplace());
            workplaceVO.setNumOf40AgeWorkplace(workplaceVO.getNumOf40AgeWorkplace() + workplace.getNumOf40AgeWorkplace());
            workplaceVO.setNumOf50AgeWorkplace(workplaceVO.getNumOf50AgeWorkplace() + workplace.getNumOf50AgeWorkplace());
            workplaceVO.setNumOf60AgeWorkplace(workplaceVO.getNumOf60AgeWorkplace() + workplace.getNumOf60AgeWorkplace());
            //남성 연령대 직장 인구
            workplaceVO.setNumOf10MenWorkplace(workplaceVO.getNumOf10MenWorkplace() + workplace.getNumOf10MenWorkplace());
            workplaceVO.setNumOf20MenWorkplace(workplaceVO.getNumOf20MenWorkplace() + workplace.getNumOf20MenWorkplace());
            workplaceVO.setNumOf30MenWorkplace(workplaceVO.getNumOf30MenWorkplace() + workplace.getNumOf30MenWorkplace());
            workplaceVO.setNumOf40MenWorkplace(workplaceVO.getNumOf40MenWorkplace() + workplace.getNumOf40MenWorkplace());
            workplaceVO.setNumOf50MenWorkplace(workplaceVO.getNumOf50MenWorkplace() + workplace.getNumOf50MenWorkplace());
            workplaceVO.setNumOf60MenWorkplace(workplaceVO.getNumOf60MenWorkplace() + workplace.getNumOf60MenWorkplace());
            //여성 연령대 직장 인구
            workplaceVO.setNumOf10WomenWorkplace(workplaceVO.getNumOf10WomenWorkplace() + workplace.getNumOf10WomenWorkplace());
            workplaceVO.setNumOf20WomenWorkplace(workplaceVO.getNumOf20WomenWorkplace() + workplace.getNumOf20WomenWorkplace());
            workplaceVO.setNumOf30WomenWorkplace(workplaceVO.getNumOf30WomenWorkplace() + workplace.getNumOf30WomenWorkplace());
            workplaceVO.setNumOf40WomenWorkplace(workplaceVO.getNumOf40WomenWorkplace() + workplace.getNumOf40WomenWorkplace());
            workplaceVO.setNumOf50WomenWorkplace(workplaceVO.getNumOf50WomenWorkplace() + workplace.getNumOf50WomenWorkplace());
            workplaceVO.setNumOf60WomenWorkplace(workplaceVO.getNumOf60WomenWorkplace() + workplace.getNumOf60WomenWorkplace());

        }
        //Map에서 workplaceVO 리스트로 반환
        List<WorkplaceVO> workplaceVOList = new ArrayList<>(workplaceMap.values());
        /**
         * 년도 내림차순, 분기 오름차순
         */
        workplaceVOList = workplaceVOList.stream().sorted(Comparator.comparing(WorkplaceVO::getYear).reversed().thenComparing(WorkplaceVO::getQuarter)).collect(Collectors.toList());
        return workplaceVOList;
    }
}
