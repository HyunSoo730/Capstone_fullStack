package capstone.fullstack.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity    //(서비스)업종 엔티티
@NoArgsConstructor
@Getter
@Setter
@Table(name = "industry_analysis") //업종 분석 테이블
public class Industry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long industryId;  //구분하기 위한 PK. 업종 id

    private int year;  //년도
    private int quarter;   //분기
    private Integer commercialCode;  //상권코드
    private String serviceName;  //서비스 업종 명
    private int numOfStores;  //점포 수
    private int totalNumOfStores; //총 점포 수(개인 + 프랜차이즈) == 유사업종 점포수
    private int numOfOpenStores;  //개업 점포 수
    private int numOfCloseStores; //폐업 점포수
    private int numOfFranchiseStores;  //프랜차이즈 점포 수

}
