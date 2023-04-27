package capstone.fullstack.api;

import capstone.fullstack.dto.RevenueDto;
import capstone.fullstack.service.RevenueApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RevenueApiController {

    private final RevenueApiService revenueApiService;

    @GetMapping("/api/revenue")
    public List<RevenueDto> callRevenueApiWithJson() {
        List<RevenueDto> result = revenueApiService.get();
        return result;
    }
}
