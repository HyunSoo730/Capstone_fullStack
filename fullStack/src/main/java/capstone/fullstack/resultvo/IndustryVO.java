package capstone.fullstack.resultvo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IndustryVO {

    private int year;  //년도
    private int quarter;   //분기
    private String dong;   //상권코드로 -> 행정동 변환해서 다시 넣기.
    private String serviceName;  //서비스 업종 명
    private int numOfStores;  //점포 수
    private int totalNumOfStores; //총 점포 수(개인 + 프랜차이즈) == 유사업종 점포수
    private int numOfOpenStores;  //개업 점포 수
    private int numOfCloseStores; //폐업 점포수
    private int numOfFranchiseStores;  //프랜차이즈 점포 수

    public IndustryVO() {

    }
}
