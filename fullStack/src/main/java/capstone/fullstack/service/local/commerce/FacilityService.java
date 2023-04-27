package capstone.fullstack.service.local.commerce;

import capstone.fullstack.domain.Facility;
import capstone.fullstack.domain.Local;
import capstone.fullstack.repository.local.commerce.FacilityRepository;
import capstone.fullstack.repository.local.commerce.LocalRepository;
import capstone.fullstack.resultvo.FacilityVO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class FacilityService {

    private final FacilityRepository facilityRepository;
    private final LocalRepository localRepository;

    public Facility findFacility(Long id) {
        return facilityRepository.findById(id).get();
    }


    public List<FacilityVO> getFacilityByDong(List<Integer> allCommercialCode) {

        List<Facility> all = facilityRepository.findByCommercialCodeIn(allCommercialCode);
        Map<String, FacilityVO> resultMap = new HashMap<>();

        for (Facility facility : all) {
            Local findLocal = localRepository.findFirstByCommercialCode(facility.getCommercialCode());
            String convertedDong = findLocal.getDong();  //상궈코드 -> 행정동

            String key = String.valueOf(facility.getYear()) + String.valueOf(facility.getQuarter());
            FacilityVO facilityVO = resultMap.computeIfAbsent(key, k -> {
                FacilityVO newFacilityVO = new FacilityVO();
                newFacilityVO.setYear(facility.getYear());
                newFacilityVO.setQuarter(facility.getQuarter());
                newFacilityVO.setDong(convertedDong);

                return newFacilityVO;
            });


            //누적값
            facilityVO.setNumOfFacility(facilityVO.getNumOfFacility() + facility.getNumOfFacility());
            facilityVO.setNumOfGovernmentOffice(facilityVO.getNumOfGovernmentOffice() + facility.getNumOfGovernmentOffice());
            facilityVO.setNumOfBank(facilityVO.getNumOfBank() + facility.getNumOfBank());
            facilityVO.setNumOfGeneralHospital(facilityVO.getNumOfGeneralHospital() + facility.getNumOfGeneralHospital());
            facilityVO.setNumOfHospital(facilityVO.getNumOfHospital() + facility.getNumOfHospital());
            facilityVO.setNumOfPharmacy(facilityVO.getNumOfPharmacy() + facility.getNumOfPharmacy());
            facilityVO.setNumOfKindergarten(facilityVO.getNumOfKindergarten() + facility.getNumOfKindergarten());
            facilityVO.setNumOfElementarySchool(facilityVO.getNumOfElementarySchool() + facility.getNumOfElementarySchool());
            facilityVO.setNumOfMiddleSchool(facilityVO.getNumOfMiddleSchool() + facility.getNumOfMiddleSchool());
            facilityVO.setNumOfHighSchool(facilityVO.getNumOfHighSchool() + facility.getNumOfHighSchool());
            facilityVO.setNumOfUniversity(facilityVO.getNumOfUniversity() + facility.getNumOfUniversity());
            facilityVO.setNumOfDepartmentStore(facilityVO.getNumOfDepartmentStore() + facility.getNumOfDepartmentStore());
            facilityVO.setNumOfSupermarket(facilityVO.getNumOfSupermarket() + facility.getNumOfSupermarket());
            facilityVO.setNumOfTheater(facilityVO.getNumOfTheater() + facility.getNumOfTheater());
            facilityVO.setNumOfAccommodation(facilityVO.getNumOfAccommodation() + facility.getNumOfAccommodation());
            facilityVO.setNumOfAirport(facilityVO.getNumOfAirport() + facility.getNumOfAirport());
            facilityVO.setNumOfRailStation(facilityVO.getNumOfRailStation() + facility.getNumOfRailStation());
            facilityVO.setNumOfBusTerminal(facilityVO.getNumOfBusTerminal() + facility.getNumOfBusTerminal());
            facilityVO.setNumOfSubway(facilityVO.getNumOfSubway() + facility.getNumOfSubway());
            facilityVO.setNumOfBusStop(facilityVO.getNumOfBusStop() + facility.getNumOfBusStop());
        }

        //Map에서 리스트로 꺼내기
        List<FacilityVO> facilityVOList = new ArrayList<>(resultMap.values());
        return facilityVOList;

    }
}
