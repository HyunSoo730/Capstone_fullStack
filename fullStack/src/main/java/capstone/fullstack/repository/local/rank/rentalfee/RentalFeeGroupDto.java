package capstone.fullstack.repository.local.rank.rentalfee;

import lombok.Data;

@Data
public class RentalFeeGroupDto {

    private Integer year;
    private String areaName;
    private Integer rentalFee;

    public RentalFeeGroupDto(Integer year, String areaName, Integer rentalFee) {
        this.year = year;
        this.areaName = areaName;
        this.rentalFee = rentalFee;
    }
}
