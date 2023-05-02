package capstone.fullstack.service.local.commerce;

import capstone.fullstack.domain.Sales;
import capstone.fullstack.repository.local.commerce.SalesRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
@Slf4j
class SalesServiceTest {

    @Autowired
    private SalesService salesService;
    @Autowired
    private SalesRepository salesRepository;

    @Test
    void 조회() {
        Optional<Sales> findSales = salesRepository.findById(1L);
        Sales sales = findSales.get();
    }
}