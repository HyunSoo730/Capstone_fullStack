package capstone.fullstack.service.local.commerce;

import capstone.fullstack.domain.Local;
import capstone.fullstack.domain.Sales;
import capstone.fullstack.repository.local.commerce.LocalRepository;
import capstone.fullstack.repository.local.commerce.SalesRepository;
import capstone.fullstack.resultvo.SalesVO;
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
}
