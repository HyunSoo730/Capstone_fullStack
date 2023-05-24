package capstone.fullstack.dto.comment;

import capstone.fullstack.domain.Comment;
import capstone.fullstack.dto.UserInfoDto;
import lombok.Data;

@Data
public class ReCommentInfoDto {

    private final static String DEFAULT_DELETE_MESSAGE="삭제된 댓글입니다.";

    private Long postId;
    private Long parentId;

    private Long reCommentId;
    private String content;
    private boolean isRemoved;

    private UserInfoDto writerDto;


    public ReCommentInfoDto(Comment reComment){
        this.postId = reComment.getId();
        this.parentId = reComment.getParent().getId();
        this.reCommentId = reComment.getId();
        this.content = reComment.getContent();

        if(reComment.isRemoved())
            this.content = DEFAULT_DELETE_MESSAGE;

        this.isRemoved = reComment.isRemoved();
        this.writerDto = new UserInfoDto(reComment.getWriter());
    }
}
