package capstone.fullstack.repository.local.rank.rentalfee;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RentalFeeRankRepository {

    private final EntityManager em;

    public List<RentalFeeGroupDto> findAllRentalFeeGroup(){
        return em.createQuery("select new capstone.fullstack.repository.local.rank.rentalfee.RentalFeeGroupDto(f.year, f.area_name, f.rentalfee_total) FROM RentalFee f WHERE f.year IN (2022, 2021) GROUP BY f.year, f.area_name")
                .getResultList();
    }

    public List<RentalFeeGroupDto> findRentalFeeGroupLastYear(){
        return em.createQuery("select new capstone.fullstack.repository.local.rank.rentalfee.RentalFeeGroupDto(f.year, f.area_name, f.rentalfee_total) FROM RentalFee f WHERE f.year = 2022 GROUP BY f.area_name")
                .getResultList();
    }
}
