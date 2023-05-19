package capstone.fullstack.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "local")  // 상권코드로 자치구, 행정동, 면적을 찾는 테이블
public class Local {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long localId; //id를 pk로

    private String borough;  //자치구

    private String dong;  //행정동

    private Integer commercialCode;  //상권코드

    private Integer area;  //해당 행정동 면적
}
