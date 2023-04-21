package capstone.fullstack.service.local.commerce;

import capstone.fullstack.domain.CommerceChange;
import capstone.fullstack.repository.local.commerce.CommerceChangeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

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


}
