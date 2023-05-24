package capstone.fullstack.dto.post;

import capstone.fullstack.domain.*;
import capstone.fullstack.domain.Post;
import capstone.fullstack.dto.UserInfoDto;
import capstone.fullstack.dto.comment.CommentInfoDto;
import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

@Getter
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

//        Map<Comment, List<Comment>> commentListMap = post.getCommentList().stream()
//                .filter(comment -> comment.getParent() != null)
//                .collect(Collectors.groupingBy(Comment::getParent));

        // post의 댓글을 대댓글로 묶어서 가져오기
        Map<Comment, List<Comment>> resultMap = getCommentListMap(post);


        /**
         * 댓글과 대댓글을 통해 CommentInfoDto 생성
         */
        commentInfoDtoList = resultMap.keySet().stream()
                .map(comment -> new CommentInfoDto(comment, resultMap.get(comment)))
                .toList();

    }

    private static Map<Comment, List<Comment>> getCommentListMap(Post post) {
        // 댓글 (대댓글이 없는 댓글 + 대댓글이 있는 댓글
        List<Comment> comments = post.getCommentList().stream()
                .filter(comment -> comment.getParent() == null)
                .collect(Collectors.toList());
        // 대댓글이 있는 댓글들
        Map<Comment, List<Comment>> collect = post.getCommentList().stream()
                .filter(comment -> comment.getParent() != null)
                .collect(Collectors.groupingBy(Comment::getParent));

        Map<Comment, List<Comment>> resultMap = new HashMap<>();
        for(Comment comment : comments){
            if(collect.keySet().contains(comment)){
                resultMap.put(comment, collect.get(comment));
            }else{
                resultMap.put(comment, null);
            }
        }
        return resultMap;
    }
}
