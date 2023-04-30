package capstone.fullstack.repository.local.commerce.population;

import capstone.fullstack.domain.population.FloatingPopulation;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FloatingRepository {

    private final EntityManager em;

    public List<FloatingPopulation> findFloatingPopulation(List<Integer> areaCodes){
        List<FloatingPopulation> resident = em.createQuery("select f from FloatingPopulation f WHERE f.commercial_code IN :areaCodes AND f.year IN (2021, 2022)", FloatingPopulation.class)
                .setParameter("areaCodes", areaCodes)
                .getResultList();

        return resident;
    }

    public List <FloatingPopulation> findFloatingPopulationInCommercial(Integer commercialCode){
        List<FloatingPopulation> populations = em.createQuery("select f from FloatingPopulation f WHERE f.commercial_code = :commercialCode AND f.year IN (2021, 2022)", FloatingPopulation.class)
                .setParameter("commercialCode", commercialCode)
                .getResultList();
        return populations;
    }
}
