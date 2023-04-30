package capstone.fullstack.repository.local.commerce;

import capstone.fullstack.domain.AvgOperationPeriod;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AvgOperationPeriodRepository {

    private final EntityManager em;

    public AvgOperationPeriod findAvgPeriodByAreaName(String areaName){
        AvgOperationPeriod period = em.createQuery("select a from AvgOperationPeriod a WHERE a.dong_name = :areaName AND a.service_name = '전체' AND a.year = 2022 AND a.quarter = 4", AvgOperationPeriod.class)
                .setParameter("areaName", areaName)
                .getResultList().get(0);
        return period;
    }

    public AvgOperationPeriod findAvgPeriodByAreaNameAndServiceName(String areaName, String serviceName){
        AvgOperationPeriod period = em.createQuery("select a from AvgOperationPeriod a WHERE a.dong_name = :areaName AND a.service_name = :serviceName AND a.year = 2022 AND a.quarter = 4", AvgOperationPeriod.class)
                .setParameter("areaName", areaName)
                .setParameter("serviceName", serviceName)
                .getResultList().get(0);
        return period;
    }
}
