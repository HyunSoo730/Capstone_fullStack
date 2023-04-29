package capstone.fullstack.resultvo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RentalFeeVO {
    private int year;
    private int quarter;

    private Integer rentalfee_total;
    private Integer rentalfee_1st_floor;
    private Integer rentalfee_except_1st_floor;
}
