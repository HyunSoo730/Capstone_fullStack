package capstone.fullstack.service.local.commerce;

import capstone.fullstack.domain.Local;
import capstone.fullstack.domain.Sales;
import capstone.fullstack.domain.rank.SalesGrowthRate;
import capstone.fullstack.repository.local.commerce.LocalRepository;
import capstone.fullstack.repository.local.commerce.SalesRepository;
import capstone.fullstack.repository.rank.SalesGrowthRateRepository;
import capstone.fullstack.resultvo.SalesVO;
import capstone.fullstack.service.local.commerce.all.AllService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class SalesService {

    private final SalesRepository salesRepository;
    private final LocalRepository localRepository;
    private final SalesGrowthRateRepository salesGrowthRateRepository;
    private final AllService allService;
    public Sales find(Long salesId) {
        Optional<Sales> temp = salesRepository.findById(salesId);
        return temp.get();
    }

    /**
     * 특정 상권 코드에 해당하는 sales 모두 추출
     */
    public List<Sales> findAllSales(Integer commercialCode, String serviceName) {
        List<Sales> sales = salesRepository.findByCommercialCodeAndServiceName(commercialCode, serviceName);
        return sales;
    }

    public List<SalesVO> getSalesListByDong(List<Integer> allCommercialCode, String sn) {
        List<Sales> salesList = salesRepository.findByCommercialCodeInAndServiceNameEquals(allCommercialCode, sn);
        Map<String, SalesVO> resultMap = new HashMap<>();
        for (Sales sales : salesList) {
            Local findLocal = localRepository.findFirstByCommercialCode(sales.getCommercialCode());
            String convertedDong = findLocal.getDong();  //상권코드 -> 행정동

            //이미 존재하는 SalesVO인 경우 누적
            //year, quarter 별로 누적된 VO 객체 만들고 Map에 저장
            String key = String.valueOf(sales.getYear()) + String.valueOf(sales.getQuarter());
            SalesVO salesVO = resultMap.computeIfAbsent(key, k -> {
                SalesVO newSalesVO = new SalesVO();
                newSalesVO.setYear(sales.getYear());
                newSalesVO.setQuarter(sales.getQuarter());
                newSalesVO.setDong(convertedDong);
                newSalesVO.setServiceName(sales.getServiceName());

                return newSalesVO;
            });

            //누적값 계산
            log.info("salesVO 분기당 매출 year quarter , 총 {} {} {}", salesVO.getYear(), salesVO.getQuarter(),  salesVO.getSalesPerQuarter());
            salesVO.setSalesPerQuarter(salesVO.getSalesPerQuarter() + sales.getSalesPerQuarter());
            salesVO.setWeeklySales(salesVO.getWeeklySales() + sales.getWeeklySales());
            salesVO.setWeekendSales(salesVO.getWeekendSales() + sales.getWeekendSales());

            salesVO.setMonSales(salesVO.getMonSales() + sales.getMonSales());
            salesVO.setTueSales(salesVO.getTueSales() + sales.getTueSales());
            salesVO.setWedSales(salesVO.getWedSales() + sales.getWedSales());
            salesVO.setThuSales(salesVO.getThuSales() + sales.getThuSales());
            salesVO.setFriSales(salesVO.getFriSales() + sales.getFriSales());
            salesVO.setSatSales(salesVO.getSatSales() + sales.getSatSales());
            salesVO.setSunSales(salesVO.getSunSales() + sales.getSunSales());

            salesVO.setTime1Sales(salesVO.getTime1Sales() + sales.getTime1Sales());
            salesVO.setTime2Sales(salesVO.getTime2Sales() + sales.getTime2Sales());
            salesVO.setTime3Sales(salesVO.getTime3Sales() + sales.getTime3Sales());
            salesVO.setTime4Sales(salesVO.getTime4Sales() + sales.getTime4Sales());
            salesVO.setTime5Sales(salesVO.getTime5Sales() + sales.getTime5Sales());
            salesVO.setTime6Sales(salesVO.getTime6Sales() + sales.getTime6Sales());

            salesVO.setMaleSales(salesVO.getMaleSales() + sales.getMaleSales());
            salesVO.setFemaleSales(salesVO.getFemaleSales() + sales.getFemaleSales());

            salesVO.setAge10Sales(salesVO.getAge10Sales() + sales.getAge10Sales());
            salesVO.setAge20Sales(salesVO.getAge20Sales() + sales.getAge20Sales());
            salesVO.setAge30Sales(salesVO.getAge30Sales() + sales.getAge30Sales());
            salesVO.setAge40Sales(salesVO.getAge40Sales() + sales.getAge40Sales());
            salesVO.setAge50Sales(salesVO.getAge50Sales() + sales.getAge50Sales());
            salesVO.setAge60Sales(salesVO.getAge60Sales() + sales.getAge60Sales());

            salesVO.setNumOfStores(salesVO.getNumOfStores() + sales.getNumOfStores());
        }

        //Map에서 SalesVO 리스트로 반환
        List<SalesVO> salesVOList = new ArrayList<>(resultMap.values());

        salesVOList = salesVOList.stream().sorted(Comparator.comparing(SalesVO::getYear).reversed().thenComparing(SalesVO::getQuarter)).collect(Collectors.toList());
        salesVOList.stream().forEach(salesVO -> {
            BigDecimal value = new BigDecimal((double)salesVO.getSalesPerQuarter() / salesVO.getNumOfStores());
            BigDecimal roundedValue = value.setScale(4, RoundingMode.HALF_UP); //4째자리까지.
            salesVO.setAvgSalesPerQuarter(roundedValue);
        });
        return salesVOList;
    }

    public List<SalesVO> getSalesListByAllDong(List<Integer> allCommercialCode, String sn, String dong) {
        List<Sales> salesList = salesRepository.findByCommercialCodeInAndServiceNameEquals(allCommercialCode, sn);
        Map<String, SalesVO> resultMap = new HashMap<>();
        for (Sales sales : salesList) {

            //이미 존재하는 SalesVO인 경우 누적
            //year, quarter 별로 누적된 VO 객체 만들고 Map에 저장
            String key = String.valueOf(sales.getYear()) + String.valueOf(sales.getQuarter());
            SalesVO salesVO = resultMap.computeIfAbsent(key, k -> {
                SalesVO newSalesVO = new SalesVO();
                newSalesVO.setYear(sales.getYear());
                newSalesVO.setQuarter(sales.getQuarter());
                newSalesVO.setDong(dong);
                newSalesVO.setServiceName(sales.getServiceName());

                return newSalesVO;
            });

            //누적값 계산
//            log.info("salesVO 분기당 매출 year quarter , 총 {} {} {}", salesVO.getYear(), salesVO.getQuarter(),  salesVO.getSalesPerQuarter());
            salesVO.setSalesPerQuarter(salesVO.getSalesPerQuarter() + sales.getSalesPerQuarter());
            salesVO.setWeeklySales(salesVO.getWeeklySales() + sales.getWeeklySales());
            salesVO.setWeekendSales(salesVO.getWeekendSales() + sales.getWeekendSales());

            salesVO.setMonSales(salesVO.getMonSales() + sales.getMonSales());
            salesVO.setTueSales(salesVO.getTueSales() + sales.getTueSales());
            salesVO.setWedSales(salesVO.getWedSales() + sales.getWedSales());
            salesVO.setThuSales(salesVO.getThuSales() + sales.getThuSales());
            salesVO.setFriSales(salesVO.getFriSales() + sales.getFriSales());
            salesVO.setSatSales(salesVO.getSatSales() + sales.getSatSales());
            salesVO.setSunSales(salesVO.getSunSales() + sales.getSunSales());

            salesVO.setTime1Sales(salesVO.getTime1Sales() + sales.getTime1Sales());
            salesVO.setTime2Sales(salesVO.getTime2Sales() + sales.getTime2Sales());
            salesVO.setTime3Sales(salesVO.getTime3Sales() + sales.getTime3Sales());
            salesVO.setTime4Sales(salesVO.getTime4Sales() + sales.getTime4Sales());
            salesVO.setTime5Sales(salesVO.getTime5Sales() + sales.getTime5Sales());
            salesVO.setTime6Sales(salesVO.getTime6Sales() + sales.getTime6Sales());

            salesVO.setMaleSales(salesVO.getMaleSales() + sales.getMaleSales());
            salesVO.setFemaleSales(salesVO.getFemaleSales() + sales.getFemaleSales());

            salesVO.setAge10Sales(salesVO.getAge10Sales() + sales.getAge10Sales());
            salesVO.setAge20Sales(salesVO.getAge20Sales() + sales.getAge20Sales());
            salesVO.setAge30Sales(salesVO.getAge30Sales() + sales.getAge30Sales());
            salesVO.setAge40Sales(salesVO.getAge40Sales() + sales.getAge40Sales());
            salesVO.setAge50Sales(salesVO.getAge50Sales() + sales.getAge50Sales());
            salesVO.setAge60Sales(salesVO.getAge60Sales() + sales.getAge60Sales());

            salesVO.setNumOfStores(salesVO.getNumOfStores() + sales.getNumOfStores());
        }

        //Map에서 SalesVO 리스트로 반환
        List<SalesVO> salesVOList = new ArrayList<>(resultMap.values());
        salesVOList = salesVOList.stream().sorted(Comparator.comparing(SalesVO::getYear).reversed().thenComparing(SalesVO::getQuarter)).collect(Collectors.toList());
        salesVOList.stream().forEach(salesVO -> {
            //모든 매출 정보... 다 더했기에 상권 개수 만큼 나눠줘야함.
            BigDecimal value = new BigDecimal((double)salesVO.getSalesPerQuarter() / salesVO.getNumOfStores());
            BigDecimal roundedValue = value.setScale(4, RoundingMode.HALF_UP); //4째자리까지.
            salesVO.setAvgSalesPerQuarter(roundedValue);
        });
        return salesVOList;
    }

    public List<SalesGrowthRate> saveAllDong(String serviceName) {
        //먼저 모든 세부 행정동 추출
        List<Local> localList = localRepository.findAll();
        List<String> dongs = localList.stream().map(local -> {
            return local.getDong();
        }).distinct().collect(Collectors.toList());  //총 402개의 동.

        //이제 해당 세부 행정동 별로 매출 값 얻어와야함.
        //3분기 4분기 값 각각 받아오자.

        //테이블에 저장할 것은 세부 행정동 이름(pk), 3분기 매출, 4분기 매출, (4분기 매출-3분기 매출)/3분기 * 100, 서비스 업종

        List<SalesGrowthRate> res = dongs.stream().map(dong -> {
            SalesGrowthRate salesGrowthRate = new SalesGrowthRate();
            // 먼저 해당 행정동의 특정 서비스 업종의 3분기, 4분기 매출값을 가져오자.
            List<Integer> allCommercialCode = allService.findAllCommercialCode(dong);  //해당 행정동에 속하는 모든 상권 코드.
            List<Sales> q3_list = salesRepository.findByCommercialCodeInAndServiceNameAndYearAndQuarter(allCommercialCode, serviceName, 2022, 3);
            List<Sales> q4_list = salesRepository.findByCommercialCodeInAndServiceNameAndYearAndQuarter(allCommercialCode, serviceName, 2022, 4);


            Long q3Sales = q3_list.stream().mapToLong(Sales::getSalesPerQuarter).sum();  //2022년도 3분기 특정 행정동의 특정 서비스 업종의 분기당 매출 총액
            Long q4Sales = q4_list.stream().mapToLong(Sales::getSalesPerQuarter).sum();  //4분기 매출총액
            double sum1 = q3Sales.doubleValue();
            double sum2 = q4Sales.doubleValue();

            if (sum1 != 0) {   //존재하지 않는 상권인 경우 넘겨야함.
                String ra = String.format("%.4f", ((sum2 - sum1) / sum1) * 100);  //소숫점 4째자리에서 반환
                salesGrowthRate.setDong(dong);
                salesGrowthRate.setServiceName(serviceName);
                salesGrowthRate.setQuarterThreeTotal(q3Sales);
                salesGrowthRate.setQuarterFourTotal(q4Sales);
                salesGrowthRate.setGrowthRateFigures(Double.valueOf(ra));
            }
            return salesGrowthRate;
//            log.info("3분기 총액 : {}, 4분기 총액: {}", q3Sales, q4Sales);
////            return salesGrowthRate;
//            log.info("다시 체크 : {}", ra);

//            Double ratio = Double.valueOf(0);
//            try {
//                ratio = Double.valueOf(((q4Sales - q3Sales) / q3Sales) * 100);   //증가율
//                log.info("비율 : {}", ratio);
//
//            } catch (Exception e) {
//                log.info("에러 발생 시 3분기: {}, 4분기: {}", q3Sales, q4Sales);
//            }
//
//
//            salesGrowthRate.setDong(dong);
//            salesGrowthRate.setServiceName(serviceName);
//            salesGrowthRate.setQuarterThreeTotal(q3Sales);
//            salesGrowthRate.setQuarterFourTotal(q4Sales);
//            salesGrowthRate.setGrowthRateFigures(ratio);
//
//            //해당 행정동의 특정 서비스 업종의 매출 관련 엔티티 생성 완료
//            return salesGrowthRate;


        }).collect(Collectors.toList());
        //즉 현재 서비스 업종만 받아온다면 모든 세부 행정동에 대한 매출 증감율을 얻어냄.
//        List<SalesGrowthRate> lists = salesGrowthRateRepository.saveAll(res);
        res = res.stream().filter(salesGrowthRate -> salesGrowthRate.getQuarterThreeTotal() != null).collect(Collectors.toList());

        //해당 데이터 테이블에 따로 저장.
        salesGrowthRateRepository.saveAll(res);

        return res;
    }

}
