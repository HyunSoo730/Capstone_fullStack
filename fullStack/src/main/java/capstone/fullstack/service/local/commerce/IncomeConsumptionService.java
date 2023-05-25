package capstone.fullstack.service.local.commerce;

import capstone.fullstack.domain.IncomeConsumption;
import capstone.fullstack.domain.Local;
import capstone.fullstack.repository.local.commerce.IncomeConsumptionRepository;
import capstone.fullstack.repository.local.commerce.LocalRepository;
import capstone.fullstack.resultvo.IncomeConsumptionVO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
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
        log.info("코드 확인 {}", all.stream().collect(Collectors.toList()));

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
        res = res.stream().sorted(Comparator.comparing(IncomeConsumptionVO::getYear).reversed().thenComparing(IncomeConsumptionVO::getQuarter)).collect(Collectors.toList());
        // 상권 코드 개수로 나눈걸로 반환해야함
//        int cnt = allCommercialCode.size();
//        res.stream().forEach(incomeConsumptionVO -> {
//            BigDecimal value = new BigDecimal((double)incomeConsumptionVO.getAverageMonthlyIncome() / cnt);
//            BigDecimal roundedValue = value.setScale(4, RoundingMode.HALF_UP); //4째자리까지.
//            incomeConsumptionVO.setAverageMonthlyIncome(Long.valueOf(String.valueOf(roundedValue)));
//            BigDecimal value2 = new BigDecimal((double)incomeConsumptionVO.getTotalAmountSpent() / cnt);
//            BigDecimal roundedValue2 = value2.setScale(4, RoundingMode.HALF_UP); //4째자리까지.
//            incomeConsumptionVO.setAverageMonthlyIncome(Long.valueOf(String.valueOf(roundedValue2)));
//        });

        return res;

    }

    public List<IncomeConsumptionVO> getIncomeConsumptionByAllDong(List<Integer> allCommercialCode, String dong) {

        List<IncomeConsumption> all = incomeConsumptionRepository.findByCommercialCodeIn(allCommercialCode);

        Map<String, IncomeConsumptionVO> resultMap = new HashMap<>();

        for (IncomeConsumption incomeConsumption : all) {

            if (incomeConsumption.getAverageMonthlyIncome() == null){
                continue;
            }

            //이미 존재하는 incomeConsumtionVO 인 경우 누적
            String key = String.valueOf(incomeConsumption.getYear()) + String.valueOf(incomeConsumption.getQuarter());
            IncomeConsumptionVO incomeConsumptionVO = resultMap.computeIfAbsent(key, k -> {
                IncomeConsumptionVO newVO = new IncomeConsumptionVO();
                newVO.setYear(incomeConsumption.getYear());
                newVO.setQuarter(incomeConsumption.getQuarter());
                newVO.setDong(dong);
                //미리 넣어야할 것들 넣고 시작
                return newVO;
            } );

            //누적값 계산
            incomeConsumptionVO.setAverageMonthlyIncome(incomeConsumptionVO.getAverageMonthlyIncome() + incomeConsumption.getAverageMonthlyIncome());
            incomeConsumptionVO.setTotalAmountSpent(incomeConsumptionVO.getTotalAmountSpent() + incomeConsumption.getTotalAmountSpent());
        }

        //Map에서 리스트로 꺼내서
        List<IncomeConsumptionVO> res = new ArrayList<>(resultMap.values());
        res = res.stream().sorted(Comparator.comparing(IncomeConsumptionVO::getYear).reversed().thenComparing(IncomeConsumptionVO::getQuarter)).collect(Collectors.toList());
//         상권 코드 개수로 나눈걸로 반환해야함
        int cnt = allCommercialCode.size();
        res.stream().forEach(incomeConsumptionVO -> {
            BigDecimal num = new BigDecimal(Double.toString(incomeConsumptionVO.getAverageMonthlyIncome() / cnt));
            BigDecimal res1 = num.setScale(4, RoundingMode.HALF_UP);
            BigDecimal num2 = new BigDecimal(Double.toString(incomeConsumptionVO.getTotalAmountSpent() / cnt));
            BigDecimal res2 = num2.setScale(4, RoundingMode.HALF_UP);
            incomeConsumptionVO.setAverageMonthlyIncome(res1.longValue());
            incomeConsumptionVO.setTotalAmountSpent(res2.longValue());
        });

        return res;

    }
}
