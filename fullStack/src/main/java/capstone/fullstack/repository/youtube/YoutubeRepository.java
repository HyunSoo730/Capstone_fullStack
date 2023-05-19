package capstone.fullstack.repository.youtube;

import capstone.fullstack.domain.youtube.Youtube;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface YoutubeRepository extends JpaRepository<Youtube, String> {

    List<Youtube> findByDong(String dong);

}
