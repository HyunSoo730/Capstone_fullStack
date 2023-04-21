package capstone.fullstack.repository.local.commerce;

import capstone.fullstack.domain.Sales;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesRepository extends JpaRepository<Sales, Long> {
    //기본 CRUD는 그냥 사용

}
