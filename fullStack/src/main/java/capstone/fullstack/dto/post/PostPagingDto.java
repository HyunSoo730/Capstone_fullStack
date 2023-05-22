//package capstone.fullstack.dto.post;
//
//import capstone.fullstack.domain.Post;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.springframework.data.domain.Page;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Data
//@NoArgsConstructor
//public class PostPagingDto {
//
//    private int totalPageCount;             //전체 페이지 수
//    private int currentPageNum;             //현재 페이지 위치
//    private long totalElementCount;         //존재하는 게시글의 총 개수
//    private int currentPageElementsCount;   //현재 페이지에 존재하는 게시글 수
//
//    private List<BriefPostInfo> simpleLectureDtoList = new ArrayList<>();
//
//    public PostPagingDto(Page<Post> searchResults) {
//        this.totalPageCount = searchResults.getTotalPages();
//        this.currentPageNum = searchResults.getNumber();
//        this.totalElementCount = searchResults.getTotalElements();
//        this.currentPageElementsCount = searchResults.getNumberOfElements();
//        this.simpleLectureDtoList = searchResults.getContent().stream().map(BriefPostInfo::new).toList();
//    }
//
//
//}
