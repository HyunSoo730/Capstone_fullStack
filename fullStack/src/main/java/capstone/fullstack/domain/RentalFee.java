package capstone.fullstack.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class RentalFee {

    @Id
    private Long id;

    private String area_name;                   //행정동 이름
    private String gu_name;                     // 구이름
    private Integer rentalfee_total;            //전체 임대료 평균
    private Integer rentalfee_1st_floor;       //1층의 임대료 평균
    private Integer rentalfee_except_1st_floor; //1층을 제외한 임대료 평균

    private Integer year;                       //년도
    private Integer quarter;                    //분기
}
