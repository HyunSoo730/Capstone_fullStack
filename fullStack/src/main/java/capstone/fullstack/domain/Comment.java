//package capstone.fullstack.domain;
//
//import capstone.fullstack.domain.user.User;
//import jakarta.persistence.*;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@NoArgsConstructor
//@Getter
//@Entity
//@Table(name = "COMMENT")
//public class Comment extends BaseTimeEntity{
//
//    @Id
//    @GeneratedValue
//    @Column(name = "comment_id")
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "writer_id")
//    private User writer;
//
//    @Lob
//    @Column(nullable = false)
//    private String content; //댓글 내용
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "post_id")
//    private Post post;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "parent_id")
//    private Comment parent;
//
//    private boolean isRemoved = false;
//
//    @OneToMany(mappedBy = "parent")
//    private List<Comment> childList = new ArrayList<>();
//
//
//    //== 연관관계 편의 메서드 ==//
//    public void confirmWriter(User writer) {
//        this.writer = writer;
//        writer.addComment(this);
//    }
//
//    public void confirmPost(Post post) {
//        this.post = post;
//        post.addComment(this);
//    }
//
//    public void confirmParent(Comment parent){
//        this.parent = parent;
//        parent.addChild(this);
//    }
//
//    public void addChild(Comment child){
//        childList.add(child);
//    }
//
//    //== 수정 ==//
//    public void updateContent(String content) {
//        this.content = content;
//    }
//    //== 삭제 ==//
//    public void remove() {
//        this.isRemoved = true;
//    }
//
//    @Builder
//    public Comment( User writer, Post post, Comment parent, String content) {
//        this.writer = writer;
//        this.post = post;
//        this.parent = parent;
//        this.content = content;
//        this.isRemoved = false;
//    }
//
//    //== 비즈니스 로직 ==//
//    public List<Comment> findRemovableList() {
//
//        List<Comment> result = new ArrayList<>();
//
//        Optional.ofNullable(this.parent).ifPresentOrElse(
//
//                parentComment ->{//대댓글인 경우 (부모가 존재하는 경우)
//                    if( parentComment.isRemoved()&& parentComment.isAllChildRemoved()){
//                        result.addAll(parentComment.getChildList());
//                        result.add(parentComment);
//                    }
//                },
//
//                () -> {//댓글인 경우
//                    if (isAllChildRemoved()) {
//                        result.add(this);
//                        result.addAll(this.getChildList());
//                    }
//                }
//        );
//
//        return result;
//    }
//
//    //모든 자식 댓글이 삭제되었는지 판단
//    private boolean isAllChildRemoved() {
//        return getChildList().stream()//https://kim-jong-hyun.tistory.com/110 킹종현님 사랑합니다.
//                .map(Comment::isRemoved)//지워졌는지 여부로 바꾼다
//                .filter(isRemove -> !isRemove)//지워졌으면 true, 안지워졌으면 false이다. 따라서 filter에 걸러지는 것은 false인 녀석들이고, 있다면 false를 없다면 orElse를 통해 true를 반환한다.
//                .findAny()//지워지지 않은게 하나라도 있다면 false를 반환
//                .orElse(true);//모두 지워졌다면 true를 반환
//
//    }
//
//}
