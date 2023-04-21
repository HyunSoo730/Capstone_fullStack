package capstone.fullstack.api.local.commerce;

import capstone.fullstack.domain.CommerceChange;
import capstone.fullstack.domain.Industry;
import capstone.fullstack.domain.Sales;
import capstone.fullstack.service.local.commerce.CommerceChangeService;
import capstone.fullstack.service.local.commerce.IndustryService;
import capstone.fullstack.service.local.commerce.SalesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommerceController {
    private final SalesService salesService;
    private final IndustryService industryService;
    private final CommerceChangeService commerceChangeService;

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
}
