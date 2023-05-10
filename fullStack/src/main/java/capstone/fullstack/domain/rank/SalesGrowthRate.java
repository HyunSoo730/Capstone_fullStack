package capstone.fullstack.domain.rank;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 매출 증가율 랭킹을 저장.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class SalesGrowthRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String dong;  //세부 행정동을 pk로

    private Integer ranking;  //랭킹

    private Double growthRateFigures;  //증가율 수치 (퍼센트 값)

    private String serviceName;  //서비스 업종 명

    private Long quarterThreeTotal;  //해당 행정동의 2022-3의 매출 총액

    private Long quarterFourTotal;  //4분기 매출 총액
}
