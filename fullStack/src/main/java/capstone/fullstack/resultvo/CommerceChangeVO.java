package capstone.fullstack.resultvo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommerceChangeVO {

    private int year;  //년도
    private int quarter;  //분기
    private String dong;  //행정동 이름
    private String commerceMetrics; //상권 변화 지표

}
