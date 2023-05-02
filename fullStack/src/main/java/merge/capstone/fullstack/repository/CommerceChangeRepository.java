package merge.capstone.fullstack.repository;

import merge.capstone.fullstack.domain.CommerceChange;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommerceChangeRepository {

    private final EntityManager em;

    public CommerceChange findCommerceChange(String areaName){
        List<CommerceChange> industryAnalyses = em.createQuery("select a from CommerceChange a WHERE a.dong = :areaName AND a.year = 2022 AND a.quarter = 4", CommerceChange.class)
                .setParameter("areaName", areaName)
                .getResultList();
        return industryAnalyses.get(0);
    }
}
