package capstone.fullstack.dto.post;

import capstone.fullstack.domain.Comment;
import capstone.fullstack.domain.Post;
import capstone.fullstack.dto.UserInfoDto;
import capstone.fullstack.dto.comment.CommentInfoDto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PostInfoDto {

    private Long postId;            //post id
    private String title;           //제목
    private String content;         //내용
    private String filePath;        //파일 경로
    private Integer view;

    private UserInfoDto writerDto;      // 작성자 정보

    private List<CommentInfoDto> commentInfoDtoList;    // 댓글 정보들


    public PostInfoDto(Post post) {

        this.postId = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.filePath = post.getFilePath();
        this.view = post.getView();

        this.writerDto = new UserInfoDto(post.getWriter());



        /**
         * 댓글과 대댓글을 그룹짓기
         * post.getCommentList()는 댓글과 대댓글이 모두 조회된다.
         */

        Map<Comment, List<Comment>> commentListMap = post.getCommentList().stream()
                .filter(comment -> comment.getParent() != null)
                .collect(Collectors.groupingBy(Comment::getParent));


        /**
         * 댓글과 대댓글을 통해 CommentInfoDto 생성
         */
        commentInfoDtoList = commentListMap.keySet().stream()
                .map(comment -> new CommentInfoDto(comment, commentListMap.get(comment)))
                .toList();

    }
}
