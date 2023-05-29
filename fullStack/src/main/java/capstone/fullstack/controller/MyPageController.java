package capstone.fullstack.controller;

import capstone.fullstack.domain.user.ReportCode;
import capstone.fullstack.domain.user.User;
import capstone.fullstack.repository.ReportCodeRepository;
import capstone.fullstack.repository.UserRepository;
import capstone.fullstack.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/mypage")
public class MyPageController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final ReportCodeRepository reportCodeRepository;

    /**
     * 사용자 정보 반환. 카카오 정보 + 닉네임
     * 사용자 정보 요청오면 토큰을 복호화해서 정보 가져온 후에 반환.
     */
    @PostMapping("/users/me")
    public ResponseEntity<Object> userInfo(@RequestHeader("Authorization") String token) {
        //JWT 토큰 검증
        log.info("프론트로부터 들어오는 token, {}", token);
        if (token == null || token.isEmpty())
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("토큰 비어있음");   //만약 정상적인 토큰 발행 안하면 에러 반환
        User user = userService.validateToken(token);  //복호화.
        return ResponseEntity.ok(user);
    }

    /**
     * 사용자 정보 수정
     * 일단은 사용자의 닉네임만 수정. 추후에 얘기해서 뭐 더 있으면 추가.
     * 커뮤니티 기능 안써서 굳이 ?
     */
    @PostMapping("/users/update/me")
    public ResponseEntity<Object> updateUser(@RequestHeader("Authorization") String token, @RequestBody UserDto dto) {
        if (token == null || token.isEmpty())
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("토큰 비어있음");   //만약 정상적인 토큰 발행 안하면 에러 반환
        User user = userService.validateToken(token);
        user.setUsername(dto.getUsername());

        //수정 완료 후 200 ok 메세지 보내기
        return ResponseEntity.ok(dto);  //HTTP 상태 코드 200 OK와 함께 userDto 객체를 반환하는 ResponseEntity객체 생성.
        //따라서 클라이언트에서 응답 결과로 dto 객체 받는다.
    }

    /**
     * 창업 희망하는 곳
     * 저장정보 저장.
     */
    @PostMapping("/users/save")
    public ResponseEntity<Object> saveData(@RequestHeader("Authorization") String token, @RequestBody ReportDto dto) {
        if (token == null || token.isEmpty())
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("토큰 비어있음");   //만약 정상적인 토큰 발행 안하면 에러 반환
        User findUser = userService.validateToken(token);
        Long userId = findUser.getUserId();
        //찾은 유저 아이디로 테이블에 저장하기.

        ReportCode reportCode = new ReportCode();

        reportCode.setUserId(userId);
        reportCode.setDong(dto.getDong());
        reportCode.setServiceName(dto.getServiceName());
        reportCode.setBorough(dto.getBorough());

        reportCodeRepository.save(reportCode);
        return ResponseEntity.ok(reportCode);
    }

    @PostMapping("/users/savedata")
    public ResponseEntity<List<ReportCode>> findData(@RequestHeader("Authorization") String token) {
        if (token == null || token.isEmpty())
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();   //만약 정상적인 토큰 발행 안하면 에러 반환
        User findUser = userService.validateToken(token);
        Long userId = findUser.getUserId();

        //해당 유저 아이디로 저장한 정보들 찾기.
        List<ReportCode> findData = reportCodeRepository.findByUserId(userId);

        return ResponseEntity.ok(findData);
    }

    @DeleteMapping("/users/delete/{id}")
    public ResponseEntity<String> deleteData(@RequestHeader("Authorization") String token, @PathVariable Integer id) {
        if (token == null || token.isEmpty())
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();   //만약 정상적인 토큰 발행 안하면 에러 반환
        User findUser = userService.validateToken(token);
        Long userId = findUser.getUserId();
        // 찾은 회원 id를 통해 DB 삭제

        reportCodeRepository.deleteById(Long.valueOf(id));

        return ResponseEntity.ok("삭제 완료");
    }

    @DeleteMapping("/users/deleteAll")
    public ResponseEntity<String> deleteAllData(@RequestHeader("Authorization") String token) {
        if (token == null || token.isEmpty())
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();   //만약 정상적인 토큰 발행 안하면 에러 반환
        User findUser = userService.validateToken(token);
        Long userId = findUser.getUserId();
        // 찾은 회원 id를 통해 DB 삭제

        reportCodeRepository.deleteAll();

        return ResponseEntity.ok("전체 삭제 완료");
    }


    @Data
    static class ReportDto {
        String borough;
        String dong;
        String serviceName;
    }

    @Data
    static class UserDto {
        private String username;
    }

}
