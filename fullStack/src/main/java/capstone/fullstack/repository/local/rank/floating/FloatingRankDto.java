package capstone.fullstack.repository.local.rank.floating;

import lombok.Data;

@Data
public class FloatingRankDto implements Comparable<FloatingRankDto>{

    private int rank;       //랭킹
    private String areaName;        //지역이름(세부동)
    private float riseRate;         //상승률 %

    public FloatingRankDto(String areaName, float riseRate) {
        this.areaName = areaName;
        this.riseRate = riseRate;
    }

    @Override
    public int compareTo(FloatingRankDto o) {
        return Float.compare(this.getRiseRate(), o.getRiseRate());
    }
}
