package capstone.fullstack.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "sales_analysis")
public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long salesId;

    private int year;
    private int quarter;
    private Long commercialCode;
    private String serviceName;
    private Long salesPerQuarter;
    private Long weeklySales;
    private Long weekendSales;
    private Long monSales;
    private Long tueSales;
    private Long wedSales;
    private Long thuSales;
    private Long friSales;
    private Long satSales;
    private Long sunSales;
    private Long time1Sales;
    private Long time2Sales;
    private Long time3Sales;
    private Long time4Sales;
    private Long time5Sales;
    private Long time6Sales;
    private Long maleSales;
    private Long femaleSales;
    private Long age10Sales;
    private Long age20Sales;
    private Long age30Sales;
    private Long age40Sales;
    private Long age50Sales;
    private Long age60Sales;
    private Long numOfStores;
}
