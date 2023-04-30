package capstone.fullstack.repository.local.commerce;

import capstone.fullstack.domain.Industry;
import capstone.fullstack.domain.Sales;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalesRepository extends JpaRepository<Sales, Long> {
    //기본 CRUD는 그냥 사용

    List<Sales> findByCommercialCodeAndServiceName(Integer commercialCode, String serviceName);

    List<Sales> findByCommercialCodeInAndServiceNameEquals(List<Integer> commercialCodeList, String serviceName);  //코드 하나라도 일치하면서 서비스 업종명 동일.


}
