//package capstone.fullstack.service.post;
//
//import capstone.fullstack.domain.user.User;
//import capstone.fullstack.dto.post.*;
//import org.springframework.data.domain.Pageable;
//
//public interface PostService {
//
//    /**
//     *  게시글 등록
//     */
//    void save(User user, PostSaveDto postSaveDto) ;
//
//    /**
//     * 게시글 수정
//     */
//    void update(User user, Long id, PostUpdateDto postUpdateDto);
//
//    /**
//     * 게시글 삭제
//     */
//    void delete(User user, Long id);
//
//    /**
//     * 게시글 1개 조회
//     */
//    PostInfoDto getPostInfo(Long id);
//
//    /**
//     * 검색조건에 따른 게시글 리스트 조회 + 페이징
//     */
//    PostPagingDto getPostList(Pageable pageable, PostSearchCondition postSearchCondition);
//}
