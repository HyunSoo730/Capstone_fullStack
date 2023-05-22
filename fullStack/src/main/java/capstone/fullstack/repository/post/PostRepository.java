//package capstone.fullstack.repository.post;
//
//import capstone.fullstack.domain.Post;
//import org.springframework.data.jpa.repository.EntityGraph;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.Optional;
//
//public interface PostRepository extends JpaRepository<Post, Long>, CustomPostRepository {
//
//    // fetch join으로 Post와 User 함께 가져오기
//    @EntityGraph(attributePaths = {"writer"})
//    Optional<Post> findWithWriterById(Long id);
//}
