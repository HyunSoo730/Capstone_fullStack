package capstone.fullstack.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@AllArgsConstructor
@Table(name = "income_consumption")  //소득소비 테이블
public class IncomeConsumption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  //소득수준 id로 pk

    private int year;  //기준 년
    private int quarter; //기준 분기
    private int commercialCode;  //상권 코드

    private Long averageMonthlyIncome;  //월 평균 소득 -> 해당 상권 내 거주한 인구의 추정 소득금액
    private Long totalAmountSpent;  //지출 총 금액

    public IncomeConsumption() {
        this.averageMonthlyIncome = 0L;
        this.totalAmountSpent = 0L;
    }
    //서울시 평균 소득 금액은 그냥 따로 기입

    public Long getTotalAmountSpent() {   //이거 때문인데... ㅣㅇ해가 안감
        return totalAmountSpent != null ? totalAmountSpent : 0L;
    }

}