package capstone.fullstack.repository.local.commerce;

import capstone.fullstack.domain.RentalFee;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
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

    public List<RentalFee> findRentalFeeForTotalDong(String gu, String dong){

        String findDong = dong + "%";
        List<RentalFee> result = em.createQuery("select f from RentalFee f WHERE f.area_name LIKE :findDong AND f.gu_name = :guName AND f.year IN (2022, 2021)", RentalFee.class)
                .setParameter("findDong", findDong)
                .setParameter("guName", gu)
                .getResultList();
        return result;
    }
}
