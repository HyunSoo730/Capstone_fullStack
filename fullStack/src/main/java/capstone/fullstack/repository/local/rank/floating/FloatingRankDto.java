package capstone.fullstack.repository.local.rank.floating;

import lombok.Data;

@Data
public class FloatingRankDto implements Comparable<FloatingRankDto>{

    private int rank;       //랭킹
    private String areaName;        //지역이름(세부동)
    private Float riseRate;         //상승률 %

    private Long floating2021;     // 2021년의 분기당 평균 유동인구
    private Long floating2022;     // 2022년의 분기당 평균 유동인구

    public FloatingRankDto(String areaName, float riseRate, Long floating2021, Long floating2022) {
        this.areaName = areaName;
        this.riseRate = riseRate;
        this.floating2021 = floating2021;
        this.floating2022 = floating2022;
    }

    @Override
    public int compareTo(FloatingRankDto o) {
        return Float.compare(this.getRiseRate(), o.getRiseRate());
    }
}
