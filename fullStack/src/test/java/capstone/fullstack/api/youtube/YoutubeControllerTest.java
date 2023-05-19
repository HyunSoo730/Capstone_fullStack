package capstone.fullstack.api.youtube;

import capstone.fullstack.domain.youtube.VideoCount;
import capstone.fullstack.domain.youtube.Youtube;
import capstone.fullstack.repository.youtube.VideoCountRepository;
import capstone.fullstack.repository.youtube.YoutubeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class YoutubeControllerTest {

    @Autowired
    YoutubeRepository youtubeRepository;
    @Autowired
    VideoCountRepository videoCountRepository;

    @Test
    void findAllEntity() {
        List<Youtube> all = youtubeRepository.findAll();
        List<VideoCount> vc = videoCountRepository.findAll();
    }


}