package merge.capstone.fullstack.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class LocalRepository {

    private final EntityManager em;

    public List<Integer> findCodesByAreaName(String areaName){
        List<Integer> areaCodes = em.createQuery("select l.commercial_code from Local l WHERE l.dong = :areaName")
                .setParameter("areaName", areaName).getResultList();
        log.info("areaCode={}", areaCodes);
        return areaCodes;
    }

}
