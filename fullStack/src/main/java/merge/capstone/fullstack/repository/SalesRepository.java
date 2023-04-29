package merge.capstone.fullstack.repository;

import merge.capstone.fullstack.domain.Sales;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class SalesRepository {

    private final EntityManager em;

    public List<Sales> findByCommercialCodes(List<Integer> areaCodes, int year, int quarter){

        List<Sales> result = em.createQuery("select s from Sales s WHERE s.commercial_code IN :areaCodes AND s.year = :year AND s.quarter = :quarter", Sales.class)
                .setParameter("areaCodes", areaCodes)
                .setParameter("year", year)
                .setParameter("quarter", quarter)
                .getResultList();

        return result;
    }
    public List<Sales> findByCommercialCodesAndServiceName(List<Integer> areaCodes, String serviceName, int year, int quarter){

        List<Sales> result = em.createQuery("select s from Sales s WHERE s.commercial_code IN :areaCodes AND s.year = :year AND s.quarter = :quarter AND s.service_name = :serviceName", Sales.class)
                .setParameter("areaCodes", areaCodes)
                .setParameter("serviceName", serviceName)
                .setParameter("year", year)
                .setParameter("quarter", quarter)
                .getResultList();
        return result;
    }


}
