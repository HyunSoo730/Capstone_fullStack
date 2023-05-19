package capstone.fullstack.repository.local.custom;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class LocalSimpleRepository {

    private final EntityManager em;

    public List<Integer> findCommercialCodesCustom(String gu, String dong) {
        String findDong = dong + "%";
        List<Integer> result = em.createQuery("select l.commercialCode from Local l WHERE l.borough = :gu AND l.dong LIKE :findDong")
                .setParameter("gu", gu)
                .setParameter("findDong", findDong)
                .getResultList();
        log.info("codes={}", result);
        return result;
    }
}
