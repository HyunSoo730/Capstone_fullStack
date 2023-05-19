package merge.capstone.fullstack.controller;

import merge.capstone.fullstack.domain.User;
import merge.capstone.fullstack.repository.UserRepository;
import merge.capstone.fullstack.service.UserService;
import merge.capstone.fullstack.web.form.UpdateUserForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    //유저 정보를 캐시로 넘겨주면 캐시에서 유저정보를 불러와야함
    @GetMapping("/mypage/{id}")
    public UserDTO myPage(@PathVariable Long id) {
        User user = userRepository.findById(id);
        UserDTO userDTO = new UserDTO(user.getEmail(), user.getNickname(), user.getWishArea());
        return userDTO;
    }


    @PostMapping("/mypage/{id}/edit")
    public UserDTO editMyPage(@PathVariable Long id, @Validated @RequestBody UpdateUserForm form, BindingResult bindingResult) {

        if (!userRepository.usableEmail(form.getEmail())) {
            bindingResult.reject("notUsableEmail", null, null);
        }

        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return null;
        }

        UserDTO userParam = new UserDTO(form.getEmail(), form.getNickname(), form.getWishArea());
        userParam.setEmail(form.getEmail());
        userParam.setNickname(form.getNickname());
        userParam.setWishArea(form.getWishArea());

        userService.update(id, userParam);
        return userParam;
    }
}
