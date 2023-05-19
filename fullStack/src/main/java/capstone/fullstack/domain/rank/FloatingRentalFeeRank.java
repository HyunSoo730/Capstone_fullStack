package capstone.fullstack.domain.rank;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter @Getter
@NoArgsConstructor
public class FloatingRentalFeeRank implements Comparable<FloatingRentalFeeRank>{

    @Id
    private int ranking;        //랭킹
    private String areaName;      //지역 이름
    private float value;        // 유동인구 / 임대료 값

    private Long floating;      // 22년 평균 유동인구
    private int rentalFee;      // 22년 평균 임대료

    public FloatingRentalFeeRank(String areaName, float value, Long floating, int rentalFee) {
        this.areaName = areaName;
        this.value = value;
        this.floating = floating;
        this.rentalFee = rentalFee;
    }

    @Override
    public int compareTo(FloatingRentalFeeRank o) {
        return Float.compare(value, o.getValue());
    }
}
