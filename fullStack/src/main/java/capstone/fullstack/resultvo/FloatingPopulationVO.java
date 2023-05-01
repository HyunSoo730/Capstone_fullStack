package capstone.fullstack.resultvo;

import capstone.fullstack.domain.RentalFee;
import capstone.fullstack.domain.population.FloatingPopulation;
import lombok.Data;

import java.util.List;

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

    public FloatingPopulationVO(){}

    public FloatingPopulationVO(List<FloatingPopulation> populations){
        this.year = populations.get(0).getYear();
        this.quarter = populations.get(0).getQuarter();

        int num = populations.size();
        this.total_flpop = populations.stream().mapToInt(FloatingPopulation::getTotal_flpop).sum()/num;
        this.male_flpop = populations.stream().mapToInt(FloatingPopulation::getMale_flpop).sum()/num;
        this.female_flpop = populations.stream().mapToInt(FloatingPopulation::getFemale_flpop).sum()/num;

        this.age_10_flpop = populations.stream().mapToInt(FloatingPopulation::getAge_10_flpop).sum()/num;
        this.age_20_flpop = populations.stream().mapToInt(FloatingPopulation::getAge_20_flpop).sum()/num;
        this.age_30_flpop = populations.stream().mapToInt(FloatingPopulation::getAge_30_flpop).sum()/num;
        this.age_40_flpop = populations.stream().mapToInt(FloatingPopulation::getAge_40_flpop).sum()/num;
        this.age_50_flpop = populations.stream().mapToInt(FloatingPopulation::getAge_50_flpop).sum()/num;
        this.age_60_flpop = populations.stream().mapToInt(FloatingPopulation::getAge_60_flpop).sum()/num;

        this.time_1_flpop = populations.stream().mapToInt(FloatingPopulation::getTime_1_flpop).sum()/num;
        this.time_2_flpop = populations.stream().mapToInt(FloatingPopulation::getTime_2_flpop).sum()/num;
        this.time_3_flpop = populations.stream().mapToInt(FloatingPopulation::getTime_3_flpop).sum()/num;
        this.time_4_flpop = populations.stream().mapToInt(FloatingPopulation::getTime_4_flpop).sum()/num;
        this.time_5_flpop = populations.stream().mapToInt(FloatingPopulation::getTime_5_flpop).sum()/num;
        this.time_6_flpop = populations.stream().mapToInt(FloatingPopulation::getTime_6_flpop).sum()/num;

        this.mon_flpop = populations.stream().mapToInt(FloatingPopulation::getMon_flpop).sum()/num;
        this.tue_flpop = populations.stream().mapToInt(FloatingPopulation::getTue_flpop).sum()/num;
        this.wed_flpop = populations.stream().mapToInt(FloatingPopulation::getWed_flpop).sum()/num;
        this.thu_flpop = populations.stream().mapToInt(FloatingPopulation::getThu_flpop).sum()/num;
        this.fri_flpop = populations.stream().mapToInt(FloatingPopulation::getFri_flpop).sum()/num;
        this.sat_flpop = populations.stream().mapToInt(FloatingPopulation::getSat_flpop).sum()/num;
        this.sun_flpop = populations.stream().mapToInt(FloatingPopulation::getSun_flpop).sum()/num;
    }
}
