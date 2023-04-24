package capstone.fullstack.api.local.commerce;

import capstone.fullstack.domain.*;
import capstone.fullstack.domain.population.Resident;
import capstone.fullstack.domain.population.Workplace;
import capstone.fullstack.service.local.commerce.*;
import capstone.fullstack.service.local.commerce.population.ResidentService;
import capstone.fullstack.service.local.commerce.population.WorkplaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommerceController {
    private final SalesService salesService;
    private final IndustryService industryService;
    private final CommerceChangeService commerceChangeService;
    private final WorkplaceService workplaceService;
    private final ResidentService residentService;
    private final IncomeConsumptionService incomeConsumptionService;
    private final FacilityService facilityService;
    private final LocalService localService;


    @PostMapping("/api/v1/target")
    public Sales findDong(@RequestParam("SalesId") String id) {
        Sales sales = salesService.find(Long.parseLong(id));
        return sales;
    }

    @GetMapping("/api/v2/target")
    public Industry find(@RequestParam("industryId") Long id) {
        Industry industry = industryService.find(id);
        return industry;
    }

    @GetMapping("/api/v3/target")
    public CommerceChange findCC(@RequestParam("id") Long id) {
        return commerceChangeService.find(id);
    }

    @GetMapping("/api/v4/target")
    public Workplace findWorkplace(@RequestParam Long id) {
        return workplaceService.findWorkplacePopulation(id);
    }

    @GetMapping("/api/v5/target")
    public Resident findResident(@RequestParam Long id) {
        return residentService.findResident(id);
    }

    @GetMapping("/api/v6/target")
    public IncomeConsumption findIncomeConsumption(@RequestParam Long id) {
        return incomeConsumptionService.findIncome(id);
    }

    @GetMapping("/api/v7/target")
    public Facility findFacility(@RequestParam Long id) {
        return facilityService.findFacility(id);
    }

    //지금까지의 데이터... 행정동 -> 상권코드 얻어와서 상권코드로 데이터들을 조회해야함
    @GetMapping("/api/find/dongtocode")
    public List<Local> findAllLocal(@RequestParam String dong) {
        List<Local> allLocalWithDong = localService.findAllLocalWithDong(dong);
        return allLocalWithDong;
    }
}
