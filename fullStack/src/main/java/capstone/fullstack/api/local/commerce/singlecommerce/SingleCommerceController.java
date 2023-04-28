package capstone.fullstack.api.local.commerce.singlecommerce;

import capstone.fullstack.domain.population.Resident;
import capstone.fullstack.service.local.commerce.*;
import capstone.fullstack.service.local.commerce.all.AllService;
import capstone.fullstack.service.local.commerce.population.ResidentService;
import capstone.fullstack.service.local.commerce.population.WorkplaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/single_commerce")
public class SingleCommerceController {

    private final SalesService salesService;
    private final IndustryService industryService;
    private final CommerceChangeService commerceChangeService;
    private final WorkplaceService workplaceService;
    private final ResidentService residentService;
    private final IncomeConsumptionService incomeConsumptionService;
    private final FacilityService facilityService;
    private final LocalService localService;

    private final AllService allService;

    /**
     * 해당 상권의 상주인구 정보 반환해준다.
     */
//    @GetMapping("/resident/{commercialCode}")
//    public List<Resident> findAllResident(@PathVariable Integer commercialCode) {
//        //해당 상궈의 상주인구 정보 반환해줌
//
//    }
}
