package capstone.fullstack.repository.local.rank.floating;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FloatingRankRepository {

    private final EntityManager em;

    //select year, dong, sum(total_flpop) from
    // (SELECT DISTINCT year, local.commercial_code, local.dong, total_flpop FROM floating_population JOIN local ON floating_population.commercial_code = local.commercial_code AND year IN (2021, 2022)) AS sub
    // GROUP BY year, dong;
    public List<FloatingGroupDto> findAllFloatingGroup(){

        return em.createQuery("SELECT new capstone.fullstack.repository.local.rank.floating.FloatingGroupDto(f.year, l.dong, SUM(f.total_flpop)) FROM FloatingPopulation f " +
                        "JOIN Local l ON f.commercial_code = l.commercialCode " +
                        "WHERE f.year IN (2021, 2022) " +
                        "GROUP BY f.year, l.dong")
                .getResultList();

    }

    public List<FloatingGroupDto> findFloatingGroupLastYear(){

        return em.createQuery("SELECT new capstone.fullstack.repository.local.rank.floating.FloatingGroupDto(f.year, l.dong, SUM(f.total_flpop)) FROM FloatingPopulation f " +
                        "JOIN Local l ON f.commercial_code = l.commercialCode " +
                        "WHERE f.year = 2022 " +
                        "GROUP BY l.dong")
                .getResultList();

    }

}
