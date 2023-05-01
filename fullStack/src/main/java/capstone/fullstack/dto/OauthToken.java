package capstone.fullstack.dto;

import lombok.Data;

@Data //(2)
public class OauthToken { //(1)
    private String access_token;
    private String token_type;
    private String refresh_token;
    private int expires_in;
    private String scope;
    private int refresh_token_expires_in;

}