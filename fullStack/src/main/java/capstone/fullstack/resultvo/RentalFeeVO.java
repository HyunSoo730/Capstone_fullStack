package capstone.fullstack.resultvo;

import capstone.fullstack.domain.RentalFee;
import capstone.fullstack.domain.population.FloatingPopulation;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class RentalFeeVO {
    private int year;
    private int quarter;

    private Integer rentalfee_total;
    private Integer rentalfee_1st_floor;
    private Integer rentalfee_except_1st_floor;

    public RentalFeeVO(int year, int quarter, List<RentalFee> fees){
        this.year = year;
        this.quarter = quarter;

        int num = fees.size();
        this.rentalfee_total = fees.stream().mapToInt(RentalFee::getRentalfee_total).sum()/num;
        this.rentalfee_1st_floor = fees.stream().mapToInt(RentalFee::getRentalfee_1st_floor).sum()/num;
        this.rentalfee_except_1st_floor = fees.stream().mapToInt(RentalFee::getRentalfee_except_1st_floor).sum()/num;
    }
}
