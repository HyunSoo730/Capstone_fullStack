package merge.capstone.fullstack.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class IncomeConsumption {

    @Id
    private Long id;

    private Integer year;
    private Integer quarter;
    private Integer commercial_code;

    private Long average_monthly_income;
    private Long total_amount_spent;


}
