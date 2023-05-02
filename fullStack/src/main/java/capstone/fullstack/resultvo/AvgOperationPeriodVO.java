package capstone.fullstack.resultvo;

import capstone.fullstack.domain.AvgOperationPeriod;
import capstone.fullstack.domain.RentalFee;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AvgOperationPeriodVO {
    private Integer year;   //년도
    private Integer quarter;    //분기
    private Double avg_period;      //평균 영업 기간

    public AvgOperationPeriodVO(List<AvgOperationPeriod> periods){
        this.year = periods.get(0).getYear();
        this.quarter = periods.get(0).getQuarter();

        Double avg = periods.stream().mapToDouble(AvgOperationPeriod::getAvg_period).sum();
        this.avg_period = avg/periods.size();
    }
}
