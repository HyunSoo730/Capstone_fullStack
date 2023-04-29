package merge.capstone.fullstack.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class AvgOperationPeriod {

    @Id
    private Long id;

    private String dong_name;           //행정동 이름
    private Integer dong_code;          //행정동 코드
    private String service_name;        //업종명

    private Float avg_period;           //평균 영업 기간

    private Integer year;                //년도
    private Integer quarter;             //분기
}
