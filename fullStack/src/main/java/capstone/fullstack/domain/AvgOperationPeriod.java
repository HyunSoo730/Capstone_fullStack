package capstone.fullstack.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class AvgOperationPeriod {

    @Id
    private Long id;

    private String area_name;           //행정동 이름
    private String gu_name;              //구 이름
    private String service_name;        //업종명

    private Double avg_period;           //평균 영업 기간

    private Integer year;                //년도
    private Integer quarter;             //분기
}
