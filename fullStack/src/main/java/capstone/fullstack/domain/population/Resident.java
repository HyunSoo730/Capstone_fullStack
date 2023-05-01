package capstone.fullstack.domain.population;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "resident_population")  //상주인구 테이블
public class Resident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long residentId;  //상주인구id를 pk값으로 지정

    private int year;  //기준 년도
    private int quarter;  //기준 분기
    private int commercialCode;  //상권 코드

    private int totalNumOfResidents;  //총 상주인구 수

    private int numOfMenResidents;  //남성 상주인구
    private int numOfWomenResidents;//여성 상주인구
    //연령대 상주인구
    private int numOfAge10Residents;  //10대 상주인구 수
    private int numOfAge20Residents;  //20대 상주인구 수
    private int numOfAge30Residents;  //30대 상주인구 수
    private int numOfAge40Residents;  //40대 상주인구 수
    private int numOfAge50Residents;  //50대 상주인구 수
    private int numOfAge60Residents;  //60대 상주인구 수
    //남성 상주인구
    private int numOfMen10Residents;  //남성 10대 상주인구 수
    private int numOfMen20Residents;  //남성 20대 상주인구 수
    private int numOfMen30Residents;  //남성 30대 상주인구 수
    private int numOfMen40Residents;  //남성 40대 상주인구 수
    private int numOfMen50Residents;  //남성 50대 상주인구 수
    private int numOfMen60Residents;  //남성 60대 상주인구 수
    //여성 상주인구
    private int numOfWomen10Residents;  //여성 10대 상주인구
    private int numOfWomen20Residents;  //여성 20대 상주인구
    private int numOfWomen30Residents;  //여성 30대 상주인구
    private int numOfWomen40Residents;  //여성 40대 상주인구
    private int numOfWomen50Residents;  //여성 50대 상주인구
    private int numOfWomen60Residents;  //여성 60대 상주인구

}
