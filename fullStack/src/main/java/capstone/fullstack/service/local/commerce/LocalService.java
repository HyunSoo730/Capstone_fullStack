package capstone.fullstack.service.local.commerce;

import capstone.fullstack.domain.Local;
import capstone.fullstack.repository.local.commerce.LocalRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class LocalService {

    private final LocalRepository localRepository;

    public List<Local> findAllLocalWithDong(String dong) {
        List<Local> findLocal = localRepository.findByDong(dong);
        List<Integer> commercialCode = new ArrayList<Integer>();

        findLocal.stream().forEach(local -> {
            commercialCode.add(local.getCommercialCode());
        });
        //해당 행정동에 속하는 상권코드 모두 추출.
        //        log.info("행정동 -> 상권코드 : 해당 엔티티 : {}", commercialCode);
        return findLocal;
    }


}
