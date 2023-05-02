package capstone.fullstack.service.local.commerce.population;

import capstone.fullstack.domain.Local;
import capstone.fullstack.domain.population.Resident;
import capstone.fullstack.repository.local.commerce.LocalRepository;
import capstone.fullstack.repository.local.commerce.population.ResidentRepository;
import capstone.fullstack.resultvo.ResidentVO;
import capstone.fullstack.service.local.commerce.all.AllService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ResidentService {

    private final ResidentRepository residentRepository;
    private final AllService allService;
    private final LocalRepository localRepository;

    public Resident findResident(Long id) {
        return residentRepository.findById(id).get();
    }

    /**
     * 특정 년도, 특정 분기에 해당하는 상권코드로 -> 행정동 변환해서 해당 행정동에 대한 누적해서 엔티티 반환
     */
    public List<ResidentVO> getResidentListByDong(List<Integer> commercialCodeList) {
        List<Resident> residentList = residentRepository.findByCommercialCodeIn(commercialCodeList);

        Map<String, ResidentVO> residentVOMap = new HashMap<>();

        for (Resident resident : residentList) {
            Local findLocal = localRepository.findFirstByCommercialCode(resident.getCommercialCode());
            String convertedDong = findLocal.getDong();  //상궈코드 -> 행정동

            //이미 존재하는 residentVO인 경우 누적
            //year, quarter 별로 누적
            String key = String.valueOf(resident.getYear()) + String.valueOf(resident.getQuarter());
            ResidentVO residentVO = residentVOMap.computeIfAbsent(key, k -> {
                ResidentVO newResidentVO = new ResidentVO();
                newResidentVO.setYear(resident.getYear());
                newResidentVO.setQuarter(resident.getQuarter());
                newResidentVO.setDong(convertedDong);
                //미리 지정해야 하는 값들 지정하고 시작.
                return newResidentVO;
            });

            //누적값 계산
            residentVO.setTotalNumOfResidents(residentVO.getTotalNumOfResidents() + resident.getTotalNumOfResidents());

            residentVO.setNumOfMenResidents(residentVO.getNumOfMenResidents() + resident.getNumOfMenResidents());
            residentVO.setNumOfWomenResidents(residentVO.getNumOfWomenResidents() + resident.getNumOfWomenResidents());

            residentVO.setNumOfAge10Residents(residentVO.getNumOfAge10Residents() + resident.getNumOfAge10Residents());
            residentVO.setNumOfAge20Residents(residentVO.getNumOfAge20Residents() + resident.getNumOfAge20Residents());
            residentVO.setNumOfAge30Residents(residentVO.getNumOfAge30Residents() + resident.getNumOfAge30Residents());
            residentVO.setNumOfAge40Residents(residentVO.getNumOfAge40Residents() + resident.getNumOfAge40Residents());
            residentVO.setNumOfAge50Residents(residentVO.getNumOfAge50Residents() + resident.getNumOfAge50Residents());
            residentVO.setNumOfAge60Residents(residentVO.getNumOfAge60Residents() + resident.getNumOfAge60Residents());
            //남성 상주인구
            residentVO.setNumOfMen10Residents(residentVO.getNumOfMen10Residents() + resident.getNumOfMen10Residents());
            residentVO.setNumOfMen20Residents(residentVO.getNumOfMen20Residents() + resident.getNumOfMen20Residents());
            residentVO.setNumOfMen30Residents(residentVO.getNumOfMen30Residents() + resident.getNumOfMen30Residents());
            residentVO.setNumOfMen40Residents(residentVO.getNumOfMen40Residents() + resident.getNumOfMen40Residents());
            residentVO.setNumOfMen50Residents(residentVO.getNumOfMen50Residents() + resident.getNumOfMen50Residents());
            residentVO.setNumOfMen60Residents(residentVO.getNumOfMen60Residents() + resident.getNumOfMen60Residents());
            //여성 상주인구
            residentVO.setNumOfWomen10Residents(residentVO.getNumOfWomen10Residents() + resident.getNumOfWomen10Residents());
            residentVO.setNumOfWomen20Residents(residentVO.getNumOfWomen20Residents() + resident.getNumOfWomen20Residents());
            residentVO.setNumOfWomen30Residents(residentVO.getNumOfWomen30Residents() + resident.getNumOfWomen30Residents());
            residentVO.setNumOfWomen40Residents(residentVO.getNumOfWomen40Residents() + resident.getNumOfWomen40Residents());
            residentVO.setNumOfWomen50Residents(residentVO.getNumOfWomen50Residents() + resident.getNumOfWomen50Residents());
            residentVO.setNumOfWomen60Residents(residentVO.getNumOfWomen60Residents() + resident.getNumOfWomen60Residents());

        }

        //Map에서 residentVO 리스트로 반환
        List<ResidentVO> residentVOList = new ArrayList<>(residentVOMap.values());
        /**
         * 년도 내림차순, 분기 오름차순 정렬
         */
        residentVOList = residentVOList.stream().sorted(Comparator.comparing(ResidentVO::getYear).reversed().thenComparing(ResidentVO::getQuarter)).collect(Collectors.toList());
//        residentVOList.forEach(System.out::println);
        return residentVOList;
    }

    public List<ResidentVO> getResidentListByAllDong(List<Integer> commercialCodeList, String dong) {
        List<Resident> residentList = residentRepository.findByCommercialCodeIn(commercialCodeList);

        Map<String, ResidentVO> residentVOMap = new HashMap<>();

        for (Resident resident : residentList) {

            //이미 존재하는 residentVO인 경우 누적
            //year, quarter 별로 누적
            String key = String.valueOf(resident.getYear()) + String.valueOf(resident.getQuarter());
            ResidentVO residentVO = residentVOMap.computeIfAbsent(key, k -> {
                ResidentVO newResidentVO = new ResidentVO();
                newResidentVO.setYear(resident.getYear());
                newResidentVO.setQuarter(resident.getQuarter());
                newResidentVO.setDong(dong);
                //미리 지정해야 하는 값들 지정하고 시작.
                return newResidentVO;
            });

            //누적값 계산
            residentVO.setTotalNumOfResidents(residentVO.getTotalNumOfResidents() + resident.getTotalNumOfResidents());

            residentVO.setNumOfMenResidents(residentVO.getNumOfMenResidents() + resident.getNumOfMenResidents());
            residentVO.setNumOfWomenResidents(residentVO.getNumOfWomenResidents() + resident.getNumOfWomenResidents());

            residentVO.setNumOfAge10Residents(residentVO.getNumOfAge10Residents() + resident.getNumOfAge10Residents());
            residentVO.setNumOfAge20Residents(residentVO.getNumOfAge20Residents() + resident.getNumOfAge20Residents());
            residentVO.setNumOfAge30Residents(residentVO.getNumOfAge30Residents() + resident.getNumOfAge30Residents());
            residentVO.setNumOfAge40Residents(residentVO.getNumOfAge40Residents() + resident.getNumOfAge40Residents());
            residentVO.setNumOfAge50Residents(residentVO.getNumOfAge50Residents() + resident.getNumOfAge50Residents());
            residentVO.setNumOfAge60Residents(residentVO.getNumOfAge60Residents() + resident.getNumOfAge60Residents());
            //남성 상주인구
            residentVO.setNumOfMen10Residents(residentVO.getNumOfMen10Residents() + resident.getNumOfMen10Residents());
            residentVO.setNumOfMen20Residents(residentVO.getNumOfMen20Residents() + resident.getNumOfMen20Residents());
            residentVO.setNumOfMen30Residents(residentVO.getNumOfMen30Residents() + resident.getNumOfMen30Residents());
            residentVO.setNumOfMen40Residents(residentVO.getNumOfMen40Residents() + resident.getNumOfMen40Residents());
            residentVO.setNumOfMen50Residents(residentVO.getNumOfMen50Residents() + resident.getNumOfMen50Residents());
            residentVO.setNumOfMen60Residents(residentVO.getNumOfMen60Residents() + resident.getNumOfMen60Residents());
            //여성 상주인구
            residentVO.setNumOfWomen10Residents(residentVO.getNumOfWomen10Residents() + resident.getNumOfWomen10Residents());
            residentVO.setNumOfWomen20Residents(residentVO.getNumOfWomen20Residents() + resident.getNumOfWomen20Residents());
            residentVO.setNumOfWomen30Residents(residentVO.getNumOfWomen30Residents() + resident.getNumOfWomen30Residents());
            residentVO.setNumOfWomen40Residents(residentVO.getNumOfWomen40Residents() + resident.getNumOfWomen40Residents());
            residentVO.setNumOfWomen50Residents(residentVO.getNumOfWomen50Residents() + resident.getNumOfWomen50Residents());
            residentVO.setNumOfWomen60Residents(residentVO.getNumOfWomen60Residents() + resident.getNumOfWomen60Residents());

        }

        //Map에서 residentVO 리스트로 반환
        List<ResidentVO> residentVOList = new ArrayList<>(residentVOMap.values());
        /**
         * 년도 내림차순, 분기 오름차순 정렬
         */
        residentVOList = residentVOList.stream().sorted(Comparator.comparing(ResidentVO::getYear).reversed().thenComparing(ResidentVO::getQuarter)).collect(Collectors.toList());
//        residentVOList.forEach(System.out::println);
        return residentVOList;
    }
}
