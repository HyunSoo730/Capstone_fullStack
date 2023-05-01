package capstone.fullstack.domain.youtube;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
/**
 * 유튜브에서 정보를 받아와 저장할 엔티티
 */
public class Youtube {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dong;  //돟 정보
    //-> 동의 구분. 페이징 -> 모든 동에 리스트에 저장해서 json 형식으로
    // 삼성동 -> 영상이 여러개. -> 영상 제목

    private String name;
}
