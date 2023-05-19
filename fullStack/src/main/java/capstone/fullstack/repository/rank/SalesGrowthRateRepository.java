package capstone.fullstack.repository.rank;

import capstone.fullstack.domain.rank.SalesGrowthRate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalesGrowthRateRepository extends JpaRepository<SalesGrowthRate, String> {

    List<SalesGrowthRate> findByServiceName(String sn);

}
