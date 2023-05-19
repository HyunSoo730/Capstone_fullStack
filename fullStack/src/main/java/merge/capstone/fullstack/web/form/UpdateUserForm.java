package merge.capstone.fullstack.web.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateUserForm {

    @NotNull @Email
    private String email;
    @NotNull
    private String nickname;

    private String wishArea;
}
