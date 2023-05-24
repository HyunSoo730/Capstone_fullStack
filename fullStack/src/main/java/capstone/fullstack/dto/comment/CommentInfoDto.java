package capstone.fullstack.dto.comment;

import capstone.fullstack.domain.Comment;
import capstone.fullstack.dto.UserInfoDto;
import lombok.Data;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Data
public class CommentInfoDto {

    private final static String DEFAULT_DELETE_MESSAGE="삭제된 댓글입니다.";

    private Long postId;

    private Long commentId;
    private String content;     //내용 (삭제되었으면 "삭제된 댓글입니다." 출력)
    private boolean isRemoved;  //삭제 여부

    private UserInfoDto writerDto;      //작성자 정보들

    private List<ReCommentInfoDto> reCommentListDtoList;    //대댓글 정보들


    public CommentInfoDto(Comment comment, List<Comment> reCommentList){
        this.postId = comment.getPost().getId();
        this.commentId = comment.getId();

        this.content = comment.getContent();
        if(comment.isRemoved())
            this.content = DEFAULT_DELETE_MESSAGE;

        this.isRemoved = comment.isRemoved();
        this.writerDto = new UserInfoDto(comment.getWriter());
        if(reCommentList != null)
            this.reCommentListDtoList = reCommentList.stream().map(ReCommentInfoDto::new).toList();
    }
}
