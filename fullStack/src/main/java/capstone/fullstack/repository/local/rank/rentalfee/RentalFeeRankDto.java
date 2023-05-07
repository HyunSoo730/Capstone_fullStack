package capstone.fullstack.repository.local.rank.rentalfee;

import lombok.Data;

@Data
public class RentalFeeRankDto implements Comparable<RentalFeeRankDto>{

    private int rank;       //랭킹
    private String areaName;    //지역이름 (세부동)
    private Float riseRate;     //상승률 %

    public RentalFeeRankDto(String areaName, float riseRate) {
        this.areaName = areaName;
        this.riseRate = riseRate;
    }

    @Override
    public int compareTo(RentalFeeRankDto o) {
        return Float.compare(riseRate,o.riseRate);
    }
}
