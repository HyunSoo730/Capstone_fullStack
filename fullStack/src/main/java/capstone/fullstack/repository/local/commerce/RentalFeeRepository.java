package capstone.fullstack.repository.local.commerce;

import capstone.fullstack.domain.RentalFee;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RentalFeeRepository {

    private final EntityManager em;

    public List<RentalFee> findRentalFee(String areaName){
        List<RentalFee> result = em.createQuery("select f from RentalFee f WHERE f.area_name = :areaName AND f.year IN (2022, 2021)")
                .setParameter("areaName", areaName)
                .getResultList();
        return result;
    }
}
