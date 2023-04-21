package capstone.fullstack.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "commerce_change")
public class CommerceChange {  //상권 변화 엔티티

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  //상권변화를 구분하는 PK

    private int year;  //년도
    private int quarter;  //분기
    private String dong;  //행정동 이름
    private String commerceMetrics; //상권 변화 지표
}
