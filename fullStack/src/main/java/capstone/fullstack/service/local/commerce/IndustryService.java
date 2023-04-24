package capstone.fullstack.service.local.commerce;

import capstone.fullstack.domain.Industry;
import capstone.fullstack.repository.local.commerce.IndustryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class IndustryService {

    private final IndustryRepository industryRepository;

    public Industry find(Long id) {
        Optional<Industry> findIndustry = industryRepository.findById(id);
        return findIndustry.get();
    }

}
