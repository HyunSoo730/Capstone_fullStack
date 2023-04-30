package merge.capstone.fullstack.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

//@Entity
@Getter @Setter
@Table(name = "sales_analysis")
public class Sales {

    @Id
    @Column(name = "sales_id")
    private Long id;

    private Integer year;
    private Integer quarter;
    private Integer commercial_code;
    private String service_name;

    private Long sales_per_quarter;    //분기당 매출 금액
    private Integer num_of_stores;

    //요일별 매출 금액
    private Long mon_sales;
    private Long tue_sales;
    private Long wed_sales;
    private Long thu_sales;
    private Long fri_sales;
    private Long sat_sales;
    private Long sun_sales;

    private Long weekly_sales;
    private Long weekend_sales;

    //시간대별 매출 금액
    @Column(name = "time1sales")
    private Long timeFrom00To06;
    @Column(name = "time2sales")
    private Long timeFrom06To11;
    @Column(name = "time3sales")
    private Long timeFrom11To14;
    @Column(name = "time4sales")
    private Long timeFrom14To17;
    @Column(name = "time5sales")
    private Long timeFrom17To21;
    @Column(name = "time6sales")
    private Long timeFrom21To24;

    //성별 매출 금액
    private Long male_sales;
    private Long female_sales;

    //연령대별 매출
    private Long age10Sales;
    private Long age20Sales;
    private Long age30Sales;
    private Long age40Sales;
    private Long age50Sales;
    private Long age60Sales;



}
