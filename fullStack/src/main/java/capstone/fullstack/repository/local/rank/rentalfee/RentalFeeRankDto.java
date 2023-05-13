package capstone.fullstack.repository.local.rank.rentalfee;

import lombok.Data;

@Data
public class RentalFeeRankDto implements Comparable<RentalFeeRankDto>{

    private int rank;       //랭킹
    private String areaName;    //지역이름 (세부동)
    private Float riseRate;     //상승률 %

    private int rentalFee21;   // 21년도 평균 임대료
    private int rentalFee22;   // 22년도 평균 임대료

    public RentalFeeRankDto(String areaName, float riseRate, int fee21, int fee22) {
        this.areaName = areaName;
        this.riseRate = riseRate;
        this.rentalFee21 = fee21;
        this.rentalFee22 = fee22;
    }

    @Override
    public int compareTo(RentalFeeRankDto o) {
        return Float.compare(riseRate,o.riseRate);
    }
}
