package merge.capstone.fullstack.repository;

import merge.capstone.fullstack.domain.Facility;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FacilityRepository {

    private final EntityManager em;

    public List<Facility> findFacility(List<Integer> areaCodes){
        List<Facility> facilities = em.createQuery("select f from Facility f WHERE f.commercial_code IN :areaCodes AND f.year = 2022 AND f.quarter = 4", Facility.class)
                .setParameter("areaCodes", areaCodes)
                .getResultList();
        return facilities;
    }
}
