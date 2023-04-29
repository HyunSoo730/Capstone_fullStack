package merge.capstone.fullstack;

import merge.capstone.fullstack.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final UserService userService;

    @PostConstruct
    public void init() {
//        User user = new User();
//        user.setEmail("testEmail");
//        user.setNickname("testName");
//        user.setWishArea("testArea");
//        userService.join(user);
    }
}
