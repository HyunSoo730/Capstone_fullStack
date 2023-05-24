package capstone.fullstack.dto.post;

import capstone.fullstack.domain.Post;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BriefPostInfo {

    private Long postId;

    private String title;
    private String content;
    private String writerName;
    private String createdDate;
    private Integer viewCount;

    public BriefPostInfo(Post post) {
        this.postId = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.writerName = post.getWriter().getKakaoNickname();
        this.createdDate = post.getCreatedDate().toString();
        this.viewCount = post.getView();
    }
}
