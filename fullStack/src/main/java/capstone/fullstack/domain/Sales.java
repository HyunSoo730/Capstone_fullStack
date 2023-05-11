package capstone.fullstack.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "sales_analysis")   //매출 분석 테이블
public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long salesId;    //pk

    private int year;   //해당 년도
    private int quarter;   //해당 분기
    private Integer commercialCode;  //상권 코드
    private String serviceName;   //서비스 업종 명
    private Long salesPerQuarter;  //분기당 매출

    private Long weeklySales;   //주간 매출
    private Long weekendSales;  //주말 매출
    private Long monSales;      //요일별 매출 월~일
    private Long tueSales;
    private Long wedSales;
    private Long thuSales;
    private Long friSales;
    private Long satSales;
    private Long sunSales;
    private Long time1Sales;   //시간대별 매출 총 6개로 시간대 나눔
    private Long time2Sales;
    private Long time3Sales;
    private Long time4Sales;
    private Long time5Sales;
    private Long time6Sales;
    private Long maleSales;   //남성 매출
    private Long femaleSales; //여성 매출
    private Long age10Sales;  //연령별 매출 10~60
    private Long age20Sales;
    private Long age30Sales;
    private Long age40Sales;
    private Long age50Sales;
    private Long age60Sales;
    private Long numOfStores;  //매장 수

    private String dong;  //일단 동 컬럼 추가
}
