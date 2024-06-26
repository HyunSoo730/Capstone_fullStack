package capstone.fullstack.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "facility")  //집객시설 엔티티
public class Facility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long facilityId;  //id를 pk로

    private int year; //기준 년
    private int quarter;  //기준 분기
    private int commercialCode; //상권 코드

    private int numOfFacility;  //집객시설 수
    private int numOfGovernmentOffice; //관공서 수
    private int numOfBank;  //은행 수
    private int numOfGeneralHospital;  //종합병원 수
    private int numOfHospital;  //일반병원 수
    private int numOfPharmacy;  //약국 수
    private int numOfKindergarten;  //유치원 수
    private int numOfElementarySchool;  //초등학교 수
    private int numOfMiddleSchool; //중학교 수
    private int numOfHighSchool;  //고등학교 수
    private int numOfUniversity;  //대학교 수
    private int numOfDepartmentStore;  //백화점 수
    private int numOfSupermarket;  //슈퍼마켓 수
    private int numOfTheater;  //극장 수
    private int numOfAccommodation; //숙박 시설 수
    private int numOfAirport;  //공항 수
    private int numOfRailStation;  //철도역 수
    private int numOfBusTerminal;  //버스터미널 수
    private int numOfSubway;  //지하철 역 수
    private int numOfBusStop;  //버스 정거장 수
}
