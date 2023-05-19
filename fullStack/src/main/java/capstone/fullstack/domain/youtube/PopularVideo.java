package capstone.fullstack.domain.youtube;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CollectionIdJdbcTypeCode;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PopularVideo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dong;  //돟 정보
    //-> 동의 구분. 페이징 -> 모든 동에 리스트에 저장해서 json 형식으로
    // 삼성동 -> 영상이 여러개. -> 영상 제목

    private String name;  //영상 제목

    private Integer views;  //조회수

    private String videoLink;  //영상 링크

    private Integer likes;  //좋아요 수

    private String thumbnail; //썸네일

    private LocalDate date;  //날짜 -> 날짜 정보만 가짐 (해당 년 월 일)
    //만약 시간 정보가 필요하다면 LocalDateTime으로 변경
    private String food;  //음식

    private String tag; //영상 태그


}
