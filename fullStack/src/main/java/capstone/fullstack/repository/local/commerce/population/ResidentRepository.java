package capstone.fullstack.repository.local.commerce.population;

import capstone.fullstack.domain.population.Resident;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 상주인구 레포지토리
 */
public interface ResidentRepository extends JpaRepository<Resident, Long> {

    List<Resident> findByCommercialCodeIn(List<Integer> code);

}
