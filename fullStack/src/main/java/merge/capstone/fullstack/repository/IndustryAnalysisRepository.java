package merge.capstone.fullstack.repository;

import merge.capstone.fullstack.domain.AvgOperationPeriod;
import merge.capstone.fullstack.domain.IndustryAnalysis;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class IndustryAnalysisRepository {

    private final EntityManager em;

    public List<IndustryAnalysis> findIndustryAnalysis(List<Integer> areaCodes, int year, int quarter){
        List<IndustryAnalysis> industries = em.createQuery("select a from IndustryAnalysis a WHERE a.commercial_code IN :areaCodes AND a.year = :year AND a.quarter = :quarter", IndustryAnalysis.class)
                .setParameter("areaCodes", areaCodes)
                .setParameter("year", year)
                .setParameter("quarter", quarter)
                .getResultList();
        return industries;
    }

    public List<IndustryAnalysis> findIndustryAnalysisWithServiceName(List<Integer> areaCodes, String serviceName, int year, int quarter){
        List<IndustryAnalysis> industries = em.createQuery("select a from IndustryAnalysis a WHERE a.commercial_code IN :areaCodes AND a.year = :year AND a.quarter = :quarter AND a.service_name = :serviceName", IndustryAnalysis.class)
                .setParameter("areaCodes", areaCodes)
                .setParameter("year", year)
                .setParameter("quarter", quarter)
                .setParameter("serviceName", serviceName)
                .getResultList();
        return industries;
    }

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
