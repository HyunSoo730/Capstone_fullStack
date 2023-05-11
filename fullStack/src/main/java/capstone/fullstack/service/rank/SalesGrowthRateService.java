package capstone.fullstack.service.rank;

import capstone.fullstack.domain.Sales;
import capstone.fullstack.domain.rank.SalesGrowthRate;
import capstone.fullstack.repository.local.commerce.SalesRepository;
import capstone.fullstack.repository.rank.SalesGrowthRateRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class SalesGrowthRateService {

    private final SalesGrowthRateRepository repository;
    private final SalesRepository salesRepository;

    /**
     * 분기당 매출 증가율 랭킹 저장하기.
     * 2022년 3분기 대비 2022년 4분기 증가율로 랭킹 추정.
     * 일단 모든 세부 행정동에 대해서 3분기 대비 4분기의 수치 값을 가져와야함.
     * 세부 행정동 별로 3분기 대비 4분기 값을 저장해야 할 것인가?
     */

    /**
     * 현재 서비스 업종 별로 각 행정동마다 2022년 3분기, 4분기 분기당 매출, 증감율(퍼센트) 저장해놈.
     * 이제 꺼내기만 하면 된다.
     */

    public List<SalesGrowthRate> returnTop(String serviceName) {
        List<SalesGrowthRate> salesList = repository.findByServiceName(serviceName);
        //해당 서비스 업종의 3분기,4분기 분기당 매출, 그리고 매출 증감율 얻어옴

        //이제 매출 증감율을 바탕으로 정렬 후 반환
        List<SalesGrowthRate> res = salesList.stream()
                .sorted(Comparator.comparing(SalesGrowthRate::getGrowthRateFigures).reversed())
                .collect(Collectors.toList()).subList(0, 10);

        //상위 10위까지 제공.

        return res;
    }

    public List<SalesGrowthRate> returnLow(String serviceName) {
        List<SalesGrowthRate> salesList = repository.findByServiceName(serviceName);
        //해당 서비스 업종의 3분기,4분기 분기당 매출, 그리고 매출 증감율 얻어옴

        //이제 매출 증감율을 바탕으로 정렬 후 반환
        List<SalesGrowthRate> res = salesList.stream()
                .sorted(Comparator.comparing(SalesGrowthRate::getGrowthRateFigures))
                .collect(Collectors.toList()).subList(0, 10);

        //하위 10위까지 제공.

        return res;
    }

}
