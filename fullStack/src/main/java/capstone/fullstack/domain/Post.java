package capstone.fullstack.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Table(name = "POST")
@Getter @Setter
@Entity
public class Post extends BaseTimeEntity{

    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")
    private User writer;

    @Column(length = 50, nullable = false)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @Column(columnDefinition = "integer default 0")
    private int view;

    private String filePath;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> commentList = new ArrayList<>();


    //== 연관관계 편의 메서드 ==//
//    public void confirmWriter(User writer) {
//        //writer는 변경이 불가능하므로 이렇게만 해주어도 될듯
//        this.writer = writer;
//        writer.addPost(this);
//    }
    public void addComment(Comment comment){
        //comment의 Post 설정은 comment에서 함
        commentList.add(comment);
    }


    //== 내용 수정 ==//
    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateContent(String content) {
        this.content = content;
    }

    public void updateFilePath(String filePath) {
        this.filePath = filePath;
    }
}
