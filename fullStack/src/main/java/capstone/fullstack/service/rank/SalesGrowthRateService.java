package capstone.fullstack.service.rank;

import capstone.fullstack.domain.Sales;
import capstone.fullstack.repository.local.commerce.SalesRepository;
import capstone.fullstack.repository.rank.SalesGrowthRateRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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


}
