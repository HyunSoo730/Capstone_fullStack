package capstone.fullstack.repository.youtube;

import capstone.fullstack.domain.youtube.PopularVideo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PopularVideoRepository extends JpaRepository<PopularVideo, Long> {

    List<PopularVideo> findByDong(String dong);

}
