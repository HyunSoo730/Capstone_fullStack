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
        return em.createQuery("select new capstone.fullstack.repository.local.rank.rentalfee.RentalFeeGroupDto(f.year, f.area_name, f.rentalfee_total) FROM RentalFee f GROUP BY f.year, f.area_name")
                .getResultList();
    }
}
