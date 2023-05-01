package capstone.fullstack.api.youtube;

import capstone.fullstack.domain.youtube.VideoCount;
import capstone.fullstack.domain.youtube.Youtube;
import capstone.fullstack.repository.youtube.VideoCountRepository;
import capstone.fullstack.repository.youtube.YoutubeRepository;
import capstone.fullstack.service.youtube.YoutubeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class YoutubeController {

    private final YoutubeService youtubeService;
    private final YoutubeRepository youtubeRepository;
    private final VideoCountRepository videoCountRepository;

    @GetMapping("/find-entity/{dong}")
    public List<Youtube> findAllEntity(@PathVariable String dong) {
        List<Youtube> res = youtubeService.findAllByDong(dong);
        return res;
    }

    @GetMapping("/save")  //행정동, 영상제목
    public Youtube save(@RequestParam String dong, @RequestParam String name) {
        Youtube youtube = new Youtube();
        youtube.setDong(dong);
        youtube.setName(name);
        VideoCount videoCount = videoCountRepository.findById(dong).get();

        videoCount.setCount(videoCount.getCount() + 1);
        youtubeRepository.save(youtube);
        return youtube;   //저장한 객체 반환.
    }

    @GetMapping("/return")
    public List<VideoCount> returnAllEntity() {
        return videoCountRepository.findAll();
    }
}
