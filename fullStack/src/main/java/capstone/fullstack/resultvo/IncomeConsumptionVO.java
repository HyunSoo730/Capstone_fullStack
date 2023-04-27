package capstone.fullstack.resultvo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IncomeConsumptionVO {

    private int year;  //기준 년
    private int quarter; //기준 분기
    private String dong;  //행정동

    private Long averageMonthlyIncome;  //월 평균 소득 -> 해당 상권 내 거주한 인구의 추정 소득금액
    private Long totalAmountSpent;  //지출 총 금액

    public IncomeConsumptionVO() {   //Long 자료형은 따로 초기화 해줘야 접근 가능..
        this.averageMonthlyIncome = 0L;
        this.totalAmountSpent = 0L;
    }
    //서울시 평균 소득 금액은 그냥 따로 기입

}
