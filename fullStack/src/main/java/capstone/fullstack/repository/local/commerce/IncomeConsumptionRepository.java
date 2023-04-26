package capstone.fullstack.repository.local.commerce;

import capstone.fullstack.domain.IncomeConsumption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncomeConsumptionRepository extends JpaRepository<IncomeConsumption, Long> {

    List<IncomeConsumption> findByCommercialCodeIn(List<Integer> code);

}
