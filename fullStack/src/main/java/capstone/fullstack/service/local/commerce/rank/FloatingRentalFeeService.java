package capstone.fullstack.service.local.commerce.rank;

import capstone.fullstack.domain.rank.FloatingRentalFeeRank;
import capstone.fullstack.repository.local.rank.floatingrentalfee.FloatingRentalFeeRankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FloatingRentalFeeService {

    private final FloatingRentalFeeRankRepository floatingRentalFeeRankRepository;

    /**
     * floating/rentalree 랭킹을 저장하고 개수를 반환
     */
    @Transactional
    public int savaAll(List<FloatingRentalFeeRank> floatingRentalFees){
        floatingRentalFeeRankRepository.deleteAll();
        for(FloatingRentalFeeRank fr : floatingRentalFees)
            floatingRentalFeeRankRepository.save(fr);
        return floatingRentalFees.size();
    }

    public List<FloatingRentalFeeRank> getFloatingRentalFeeRanks(){
        return floatingRentalFeeRankRepository.findAll();
    }
}
