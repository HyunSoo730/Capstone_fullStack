package merge.capstone.fullstack.repository;

import merge.capstone.fullstack.domain.IncomeConsumption;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class IncomeConsumptionRepository {

    private final EntityManager em;

    public IncomeConsumption findIncomeConsumption(List<Integer> areaCodes){
        IncomeConsumption consumption = em.createQuery("select a from IncomeConsumption a WHERE a.commercial_code IN :areaCodes AND a.year = 2022 AND a.quarter = 4", IncomeConsumption.class)
                .setParameter("areaCodes", areaCodes)
                .getResultList().get(0);
        return consumption;
    }
}
