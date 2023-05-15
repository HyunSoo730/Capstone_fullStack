package capstone.fullstack.service.comment;

import capstone.fullstack.domain.Comment;
import capstone.fullstack.domain.User;
import capstone.fullstack.dto.comment.CommentSaveDto;
import capstone.fullstack.dto.comment.CommentUpdateDto;
import capstone.fullstack.exception.CommentException;
import capstone.fullstack.exception.CommentExceptionType;
import capstone.fullstack.exception.PostException;
import capstone.fullstack.exception.PostExceptionType;
import capstone.fullstack.repository.CommentRepository;
import capstone.fullstack.repository.PostRepository;
import capstone.fullstack.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;


    @Override
    public void save(User user, Long postId, CommentSaveDto commentSaveDto) {
        Comment comment = commentSaveDto.toEntity();

        comment.confirmWriter(user);

        comment.confirmPost(postRepository.findById(postId).orElseThrow(()->new PostException(PostExceptionType.POST_NOT_FOUND)));

        commentRepository.save(comment);
    }

    @Override
    public void saveReComment(User user, Long postId, Long parentId, CommentSaveDto commentSaveDto) {
        Comment comment = commentSaveDto.toEntity();

        comment.confirmWriter(user);

        comment.confirmPost(postRepository.findById(postId).orElseThrow(() -> new PostException(PostExceptionType.POST_NOT_FOUND)));

        comment.confirmParent(commentRepository.findById(parentId).orElseThrow(()->new CommentException(CommentExceptionType.NOT_FOUND_COMMENT)));
    }

    @Override
    public void update(User user, Long id, CommentUpdateDto commentUpdateDto) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new CommentException(CommentExceptionType.NOT_FOUND_COMMENT));

        if(!comment.getWriter().equals(user)){
            throw new CommentException((CommentExceptionType.NOT_AUTHORITY_UPDATE_COMMENT));
        }
        commentUpdateDto.content().ifPresent(comment::updateContent);
    }

    @Override
    public void remove(User user, Long id) throws CommentException {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new CommentException(CommentExceptionType.NOT_FOUND_COMMENT));

        if(!comment.getWriter().equals(user)){
            throw new CommentException(CommentExceptionType.NOT_AUTHORITY_DELETE_COMMENT);
        }

        comment.remove();
        List<Comment> removableList = comment.findRemovableList();
        removableList.forEach(removableComment->commentRepository.delete(removableComment));
    }
}
