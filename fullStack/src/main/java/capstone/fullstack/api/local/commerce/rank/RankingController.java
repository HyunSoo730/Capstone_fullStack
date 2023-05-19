package capstone.fullstack.api.local.commerce.rank;

import capstone.fullstack.domain.rank.SalesGrowthRate;
import capstone.fullstack.service.rank.SalesGrowthRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RankingController {

    private final SalesGrowthRateService salesGrowthRateService;

    /**
     * 서비스 업종 별로 매출 증감율이 높은 순으로 상위 10개 반환
     */
    @GetMapping("/api/local-commerce/{serviceName}/top_rank")
    public List<SalesGrowthRate> topRanking(@PathVariable String serviceName) {
        //해당 서비스 업종의 3,4분기 분기 당 매출, 증감 퍼센트 반환. (퍼센트 값 큰걸로 반영!!)

        List<SalesGrowthRate> res = salesGrowthRateService.returnTop(serviceName);
        return res;
    }

    /**
     * 서비스 업종 별로 매출 증감율이 높은 순으로 하위 10개 반환
     */
    @GetMapping("/api/local-commerce/{serviceName}/low_rank")
    public List<SalesGrowthRate> lowRanking(@PathVariable String serviceName) {
        //해당 서비스 업종의 3,4분기 분기 당 매출, 증감 퍼센트 반환. (퍼센트 값 큰걸로 반영!!)

        List<SalesGrowthRate> res = salesGrowthRateService.returnLow(serviceName);
        return res;
    }
}
