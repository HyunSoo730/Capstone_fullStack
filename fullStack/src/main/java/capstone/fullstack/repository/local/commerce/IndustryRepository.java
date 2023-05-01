package capstone.fullstack.repository.local.commerce;

import capstone.fullstack.domain.Industry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IndustryRepository extends JpaRepository<Industry, Long> {

    List<Industry> findByCommercialCodeInAndServiceNameEquals(List<Integer> commercialCodeList, String serviceName);  //코드 하나라도 일치하면서 서비스 업종명 동일.

    //    Industry findByCommercialCode(Integer code);
    List<Industry> findByCommercialCodeAndServiceName(Integer commercialCode, String serviceName);

}
