package capstone.fullstack.service.local.commerce;

import capstone.fullstack.domain.IncomeConsumption;
import capstone.fullstack.domain.Local;
import capstone.fullstack.repository.local.commerce.IncomeConsumptionRepository;
import capstone.fullstack.repository.local.commerce.LocalRepository;
import capstone.fullstack.resultvo.IncomeConsumptionVO;
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
public class IncomeConsumptionService {

    private final IncomeConsumptionRepository incomeConsumptionRepository;
    private final LocalRepository localRepository;

    public IncomeConsumption findIncome(Long id) {
        return incomeConsumptionRepository.findById(id).get();
    }


    /**
     * 특정 년도 -> 특정 분기에 해당하는 상권코드로 월 평균 소득(해당 상권 내 거주한 인구의 추정 소득금액)
     * 지출 총 금액 얻기
     */
    public List<IncomeConsumptionVO> getIncomeConsumptionByDong(List<Integer> allCommercialCode) {

        List<IncomeConsumption> all = incomeConsumptionRepository.findByCommercialCodeIn(allCommercialCode);

        Map<String, IncomeConsumptionVO> resultMap = new HashMap<>();

        for (IncomeConsumption incomeConsumption : all) {
            Local findLocal = localRepository.findFirstByCommercialCode(incomeConsumption.getCommercialCode());
            String convertedDong = findLocal.getDong();  //상권코드 -> 행정동

            //이미 존재하는 incomeConsumtionVO 인 경우 누적
            String key = String.valueOf(incomeConsumption.getYear()) + String.valueOf(incomeConsumption.getQuarter());
            IncomeConsumptionVO incomeConsumptionVO = resultMap.computeIfAbsent(key, k -> {
                IncomeConsumptionVO newVO = new IncomeConsumptionVO();
                newVO.setYear(incomeConsumption.getYear());
                newVO.setQuarter(incomeConsumption.getQuarter());
                newVO.setDong(convertedDong);
                //미리 넣어야할 것들 넣고 시작
                return newVO;
            } );

            //누적값 계산
            incomeConsumptionVO.setAverageMonthlyIncome(incomeConsumptionVO.getAverageMonthlyIncome() + incomeConsumption.getAverageMonthlyIncome());
            incomeConsumptionVO.setTotalAmountSpent(incomeConsumptionVO.getTotalAmountSpent() + incomeConsumption.getTotalAmountSpent());
        }

        //Map에서 리스트로 꺼내서
        List<IncomeConsumptionVO> res = new ArrayList<>(resultMap.values());
        return res;

    }
}
