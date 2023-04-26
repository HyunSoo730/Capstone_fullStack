package capstone.fullstack.resultvo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data   
@AllArgsConstructor
public class WorkplaceVO {

    private int year;     // 기준 년
    private int quarter; //기분 분기
    private String dong;  //상권 코드 -> 행정동

    private int totalNumOfWorkplace;  //총 직장 인구 수
    private int numOfMenWorkplace;  //남성 직장 인구 수
    private int numOfWomenWorkplace;  //여성 직장 인구 수
    //연령대별 직장 인구 수
    private int numOf10AgeWorkplace;  //10대 직장 인구 수
    private int numOf20AgeWorkplace;  //20대 직장 인구 수
    private int numOf30AgeWorkplace;  //30대 직장 인구 수
    private int numOf40AgeWorkplace;  //40대 직장 인구 수
    private int numOf50AgeWorkplace;  //50대 직장 인구 수
    private int numOf60AgeWorkplace;  //60대 직장 인구 수
    //남성, 여성 연령대별 직장 인구
    //먼저 남성 연령대 직장 인구
    private int numOf10MenWorkplace;  //남성 10대 직장 인구 수
    private int numOf20MenWorkplace;  //남성 20대 직장 인구 수
    private int numOf30MenWorkplace;  //남성 30대 직장 인구 수
    private int numOf40MenWorkplace;  //남성 40대 직장 인구 수
    private int numOf50MenWorkplace;  //남성 50대 직장 인구 수
    private int numOf60MenWorkplace;  //남성 60대 직장 인구 수
    //여성 연령대 직장 인구
    private int numOf10WomenWorkplace;  //여성 10대 직장 인구 수
    private int numOf20WomenWorkplace;  //여성 20대 직장 인구 수
    private int numOf30WomenWorkplace;  //여성 30대 직장 인구 수
    private int numOf40WomenWorkplace;  //여성 40대 직장 인구 수
    private int numOf50WomenWorkplace;  //여성 50대 직장 인구 수
    private int numOf60WomenWorkplace;  //여성 60대 직장 인구 수

    public WorkplaceVO() {

    }
}
