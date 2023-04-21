package capstone.fullstack.service.local.commerce.population;

import capstone.fullstack.domain.population.Workplace;
import capstone.fullstack.repository.local.commerce.population.WorkplaceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class WorkplaceService {

    private final WorkplaceRepository workplaceRepository;

    public Workplace findWorkplacePopulation(Long id) {
        return workplaceRepository.findById(id).get();
    }


}
