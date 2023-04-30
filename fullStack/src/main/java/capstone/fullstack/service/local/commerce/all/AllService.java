package capstone.fullstack.service.local.commerce.all;

import capstone.fullstack.domain.Local;
import capstone.fullstack.repository.local.commerce.LocalRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AllService {

    private final LocalRepository localRepository;

    public List<Integer> findAllCommercialCode(String dong) {

        List<Integer> allCommercialCode = localRepository.findByDong(dong).stream()
                .map(local -> local.getCommercialCode()).collect(Collectors.toList()).stream().distinct().collect(Collectors.toList());

        log.info("모든 코드 확인 {}", allCommercialCode);
//        List<Integer> distinctList = allCommercialCode.stream().distinct().collect(Collectors.toList());
//        log.info("확인용 {}", distinctList);
        return allCommercialCode;
    }



//    public String findDong(Integer commercialCode) {
//        Local local = localRepository.findByCommercialCode(commercialCode);
//        return local.getDong();   //상권코드 -> 행정동
//    }


}
