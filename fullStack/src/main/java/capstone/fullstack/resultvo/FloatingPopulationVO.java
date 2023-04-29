package capstone.fullstack.resultvo;

import lombok.Data;

@Data
public class FloatingPopulationVO {
    private Integer year;           //년도
    private Integer quarter;        //분기
    private Integer total_flpop;    //평균 유동인구

    // 성별 유동인구
    private Integer male_flpop;
    private Integer female_flpop;

    //나이대별 유동인구
    private Integer age_10_flpop;
    private Integer age_20_flpop;
    private Integer age_30_flpop;
    private Integer age_40_flpop;
    private Integer age_50_flpop;
    private Integer age_60_flpop;

    //시간대별 유동인구
    private Integer time_1_flpop;   //00시 ~ 06시
    private Integer time_2_flpop;   //06시 ~ 11시
    private Integer time_3_flpop;   //11시 ~ 14시
    private Integer time_4_flpop;   //14시 ~ 17시
    private Integer time_5_flpop;   //17시 ~ 21시
    private Integer time_6_flpop;   //21시 ~ 24시

    //요일별 유동인구
    private Integer mon_flpop;
    private Integer tue_flpop;
    private Integer wed_flpop;
    private Integer thu_flpop;
    private Integer fri_flpop;
    private Integer sat_flpop;
    private Integer sun_flpop;
}
