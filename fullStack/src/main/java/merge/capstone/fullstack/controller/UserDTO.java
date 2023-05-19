package merge.capstone.fullstack.controller;

import lombok.Data;

@Data
public class UserDTO {

    public UserDTO(String email, String nickname, String wishArea) {
        this.nickname = nickname;
        this.email = email;
        this.wishArea = wishArea;
    }
    private String email;
    private String nickname;
    private String wishArea;
}
