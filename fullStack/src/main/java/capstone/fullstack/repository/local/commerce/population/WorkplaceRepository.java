package capstone.fullstack.repository.local.commerce.population;

import capstone.fullstack.domain.population.Workplace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkplaceRepository extends JpaRepository<Workplace, Long> {

    List<Workplace> findByCommercialCodeIn(List<Integer> code);

}
