package capstone.fullstack.service.local.commerce;

import capstone.fullstack.domain.Sales;
import capstone.fullstack.repository.local.commerce.SalesRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class SalesService {

    private final SalesRepository salesRepository;

    public Sales find(Long salesId) {
        Optional<Sales> temp = salesRepository.findById(salesId);
        return temp.get();
    }

}
