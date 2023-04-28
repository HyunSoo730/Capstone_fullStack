package capstone.fullstack.service.local.commerce;

import capstone.fullstack.domain.CommerceChange;
import capstone.fullstack.repository.local.commerce.CommerceChangeRepository;
import capstone.fullstack.resultvo.CommerceChangeVO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CommerceChangeService {

    private final CommerceChangeRepository commerceChangeRepository;

    public CommerceChange find(Long id) {   //id 로 찾아보기
        Optional<CommerceChange> commerceChangeEntity = commerceChangeRepository.findById(id);
        CommerceChange cc = commerceChangeEntity.get();  //상권 변화 지표 엔티티로 반환해봄
        return cc;
    }

    public List<CommerceChangeVO> findMetrics(String dong) {
        List<CommerceChange> cc = commerceChangeRepository.findByDong(dong);
        List<CommerceChangeVO> res = cc.stream().sorted(Comparator.comparing(CommerceChange::getYear).reversed().thenComparing(CommerceChange::getQuarter)).map(commerceChange -> new CommerceChangeVO(commerceChange.getYear(), commerceChange.getQuarter(), commerceChange.getDong(), commerceChange.getCommerceMetrics()))
                .collect(Collectors.toList());

        return res;
    }


}
