package capstone.fullstack.domain.user;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private Long kakaoId;

    private String kakaoProfileImg;

    private String kakaoNickname;

    private String kakaoEmail;

    private String username;  //닉네임

//    private String userRole;

    @CreationTimestamp
    private Timestamp createTime;

    //상권에 대한 정보, 행정동에 대한 분석정보


    @Builder
    public User(Long kakaoId, String kakaoProfileImg, String kakaoNickname,
                String kakaoEmail) {

        this.kakaoId = kakaoId;
        this.kakaoProfileImg = kakaoProfileImg;
        this.kakaoNickname = kakaoNickname;
        this.kakaoEmail = kakaoEmail;
//        this.userRole = userRole;
    }
}
