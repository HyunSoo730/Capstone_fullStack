package capstone.fullstack.repository.local.rank.floatingrentalfee;

import lombok.Data;

@Data
public class FloatingRentalFeeRankDto implements Comparable<FloatingRentalFeeRankDto>{

    private int rank;                        //랭킹
    private String areaName;                  //지역이름 (세부동)
    private Float floatingDivRentalFee;       //유동인구/임대료

    public FloatingRentalFeeRankDto(String areaName, float floatingDivRentalFee) {
        this.areaName = areaName;
        this.floatingDivRentalFee = floatingDivRentalFee;
    }

    @Override
    public int compareTo(FloatingRentalFeeRankDto o) {
        return Float.compare(floatingDivRentalFee, o.getFloatingDivRentalFee());
    }
}

