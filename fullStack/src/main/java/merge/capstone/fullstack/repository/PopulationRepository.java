package merge.capstone.fullstack.repository;

import capstone.fullstack.domain.*;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import merge.capstone.fullstack.domain.FloatingPopulation;
import merge.capstone.fullstack.domain.ResidentPopulation;
import merge.capstone.fullstack.domain.WorkplacePopulation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PopulationRepository {

    private final EntityManager em;

    public List<ResidentPopulation> findResidentPopulation(List<Integer> areaCodes){
        List<ResidentPopulation> resident = em.createQuery("select r from ResidentPopulation r WHERE r.commercial_code IN :areaCodes AND r.year = 2022 AND r.quarter = 4", ResidentPopulation.class)
                .setParameter("areaCodes", areaCodes)
                .getResultList();

        return resident;
    }

    public List<WorkplacePopulation> findWorkplacePopulation(List<Integer> areaCodes){
        List<WorkplacePopulation> resident = em.createQuery("select w from WorkplacePopulation w WHERE w.commercial_code IN :areaCodes AND w.year = 2022 AND w.quarter = 4", WorkplacePopulation.class)
                .setParameter("areaCodes", areaCodes)
                .getResultList();

        return resident;
    }

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
