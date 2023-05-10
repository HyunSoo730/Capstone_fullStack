package capstone.fullstack.repository.youtube;

import capstone.fullstack.domain.youtube.VideoCount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoCountRepository extends JpaRepository<VideoCount, String> {

}
