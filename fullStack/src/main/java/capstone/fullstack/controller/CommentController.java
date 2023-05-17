package capstone.fullstack.controller;

import capstone.fullstack.domain.User;
import capstone.fullstack.dto.comment.CommentSaveDto;
import capstone.fullstack.dto.comment.CommentUpdateDto;
import capstone.fullstack.service.UserService;
import capstone.fullstack.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final UserService userService;

    @PostMapping("/comment/{postId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void commentSave(@RequestHeader("Authorization") String token, @PathVariable("postId") Long postId, CommentSaveDto commentSaveDto) {
        User user = userService.validateToken(token);
        commentService.save(user, postId, commentSaveDto);
    }

    @PostMapping("/comment/{postId}/{commnetId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void reCommentSave(@RequestHeader("Authorization") String token,
                              @PathVariable("postId") Long postId,
                              @PathVariable("commentId") Long commentId,
                              CommentSaveDto commentSaveDto){
        User user = userService.validateToken(token);
        commentService.saveReComment(user, postId, commentId, commentSaveDto);
    }

    @PutMapping("/comment/{commentId}")
    public void update(@RequestHeader("Authorization") String token,
                       @PathVariable("commentId") Long commentId,
                       CommentUpdateDto commentUpdateDto){
        User user = userService.validateToken(token);
        commentService.update(user, commentId, commentUpdateDto);
    }

    @DeleteMapping("/comment/{commentId}")
    public void delete(@RequestHeader("Authorization") String token,
                       @PathVariable("commentId") Long commentId){
        User user = userService.validateToken(token);
        commentService.remove(user, commentId);
    }

}
