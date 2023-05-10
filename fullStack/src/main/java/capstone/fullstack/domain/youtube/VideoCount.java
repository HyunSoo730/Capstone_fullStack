package capstone.fullstack.domain.youtube;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class VideoCount {

    @Id
    private String dong;   //동으로 구분

    private Integer count;  //영상 개수

    private Integer maxVies; //해당 행정동 중 최대 조회수

    private Long metrics;  //히트맵에 제시해줄 지표 값. (최대 조회수 * 영상 개수)

}
