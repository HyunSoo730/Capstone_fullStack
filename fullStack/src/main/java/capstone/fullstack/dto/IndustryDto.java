package capstone.fullstack.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IndustryDto {

    private Long commercialCode;
    private String serviceName;
    private int year;
    private int quarter;

}
