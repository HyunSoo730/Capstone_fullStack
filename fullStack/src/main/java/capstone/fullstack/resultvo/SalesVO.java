package capstone.fullstack.resultvo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class SalesVO {

    private int year;   //해당 년도
    private int quarter;   //해당 분기
    private String dong;  //상권 코드 --> 행정동 누적합해서 저장.
    private String serviceName;   //서비스 업종 명
    //여기까지 기본으로 넣어주고 시작
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

    public SalesVO() {
        this.year = 0;
        this.quarter = 0;
        this.dong = "";
        this.serviceName = "";
        this.salesPerQuarter = 0L;
        this.weeklySales = 0L;
        this.weekendSales = 0L;
        this.monSales = 0L;
        this.tueSales = 0L;
        this.wedSales = 0L;
        this.thuSales = 0L;
        this.friSales = 0L;
        this.satSales = 0L;
        this.sunSales = 0L;
        this.time1Sales = 0L;
        this.time2Sales = 0L;
        this.time3Sales = 0L;
        this.time4Sales = 0L;
        this.time5Sales = 0L;
        this.time6Sales = 0L;
        this.maleSales = 0L;
        this.femaleSales = 0L;
        this.age10Sales = 0L;
        this.age20Sales = 0L;
        this.age30Sales = 0L;
        this.age40Sales = 0L;
        this.age50Sales = 0L;
        this.age60Sales = 0L;
        this.numOfStores = 0L;
    }


}
