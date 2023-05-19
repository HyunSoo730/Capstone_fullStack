package capstone.fullstack.dto;

import capstone.fullstack.domain.user.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserInfoDto {

    private String name;
    private String email;

    @Builder
    public UserInfoDto(User user){
        this.name = user.getKakaoNickname();
        this.email = user.getKakaoEmail();
    }
}
