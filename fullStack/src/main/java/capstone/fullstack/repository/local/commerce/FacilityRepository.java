package capstone.fullstack.repository.local.commerce;

import capstone.fullstack.domain.Facility;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacilityRepository extends JpaRepository<Facility, Long> {

    List<Facility> findByCommercialCodeIn(List<Integer> code);

    List<Facility> findByCommercialCode(Integer code);
}
