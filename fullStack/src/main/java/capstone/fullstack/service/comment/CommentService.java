package capstone.fullstack.service.comment;


import capstone.fullstack.domain.user.User;
import capstone.fullstack.dto.comment.CommentSaveDto;
import capstone.fullstack.dto.comment.CommentUpdateDto;
import capstone.fullstack.exception.CommentException;

import java.util.List;

public interface CommentService {

    void save(User user, Long postId, CommentSaveDto commentSaveDto);

    void saveReComment(User user, Long postId, Long parentId, CommentSaveDto commentSaveDto);

    void update(User user, Long id, CommentUpdateDto commentUpdateDto);

    void remove(User user, Long id) throws CommentException;
}
