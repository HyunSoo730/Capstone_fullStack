package capstone.fullstack.controller;

import capstone.fullstack.domain.user.User;
import capstone.fullstack.dto.post.PostSaveDto;
import capstone.fullstack.dto.post.PostSearchCondition;
import capstone.fullstack.dto.post.PostUpdateDto;
import capstone.fullstack.service.UserService;
import capstone.fullstack.service.post.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final UserService userService;

    /**
     * 게시글 저장
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/post")
    public void save(@RequestHeader("Authorization") String token, @Valid @ModelAttribute PostSaveDto postSaveDto) {
        User user = userService.validateToken(token);
        postService.save(user, postSaveDto);
    }

    /**
     * 게시글 수정
     */
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/post/{postId}")
    public void update(@RequestHeader("Authorization") String token, @PathVariable("postId") Long postId, @ModelAttribute PostUpdateDto postUpdateDto){
        User user = userService.validateToken(token);
        postService.update(user, postId, postUpdateDto);
    }

    /**
     * 게시글 삭제
     */
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/post/{postId}")
    public void delete(@RequestHeader("Authorization") String token, @PathVariable("postId") Long postId){
        User user = userService.validateToken(token);
        postService.delete(user, postId);
    }

    /**
     * 게시글 조회
     */
    @GetMapping("/post/{postId}")
    public ResponseEntity getInfo(@PathVariable("postId") Long postId) {
        return ResponseEntity.ok(postService.getPostInfo(postId));
    }

    /**
     * 게시글 검색
     */
    @GetMapping("/post")
    public ResponseEntity search(Pageable pageable, @ModelAttribute PostSearchCondition postSearchCondition) {
        return ResponseEntity.ok(postService.getPostList(pageable, postSearchCondition));
    }

}
