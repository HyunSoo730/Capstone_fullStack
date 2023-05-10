package capstone.fullstack.api.youtube;

import capstone.fullstack.domain.youtube.VideoCount;
import capstone.fullstack.domain.youtube.Youtube;
import capstone.fullstack.repository.youtube.VideoCountRepository;
import capstone.fullstack.repository.youtube.YoutubeRepository;
import capstone.fullstack.service.youtube.YoutubeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/youtube")
public class YoutubeController {

    private final YoutubeService youtubeService;
    private final YoutubeRepository youtubeRepository;
    private final VideoCountRepository videoCountRepository;

    /**
     * 프론트로부터 행정동 정보를 받으면 해당 행정동의 유튜브 엔티티 모두 반환.
     */
    @GetMapping("/find-entity/{dong}")
    public List<Youtube> findAllEntity(@PathVariable String dong) {
        List<Youtube> res = youtubeService.findAllByDong(dong);
        return res;
    }


    /**
     * 프론트로부터 DB에 저장할 유튜브 객체 받아옴.
     */
    @PostMapping("/save")  //행정동, 영상제목
    public Youtube saveEntity(@RequestBody YoutubeDto dto) {
        Youtube youtube = new Youtube();
        youtube.setDong(dto.getDong());
        youtube.setName(dto.getName());
        youtube.setDate(dto.getDate());
        youtube.setFood(dto.getFood());
        youtube.setLikes(dto.getLikes());
        youtube.setThumbnail(dto.getThumbnail());
        youtube.setVideoLink(dto.getVideoLink());
        youtube.setViews(dto.getViews());

        //DB에 넣을 유튜브 엔티티 생성 완료.
        youtubeRepository.save(youtube);  //DB에 저장

        //이제 저장한 후에 해당 행정동의 영상 개수 갱신
        VideoCount videoCount = videoCountRepository.findById(dto.getDong()).get();
        videoCount.setCount(videoCount.getCount() + 1);  //영상 개수 반환 후.

        //영상 최대 조회수 갱신
        if (youtube.getViews() > getMaxViews(videoCount))
            videoCount.setMaxVies(youtube.getViews());
        // 지표값 갱신
        videoCount.setMetrics(Long.valueOf(videoCount.getCount() * videoCount.getMaxVies()));
        //다시 DB에 저장 (갱신)
        videoCountRepository.save(videoCount);

        return youtube;   //저장한 객체 반환 시켜줌.
    }

    /**
     * 모든 동의 정보 값 반환. 지표 값 큰 순으로 반환시킴.
     */
    @GetMapping("/return")
    public List<VideoCount> returnAllEntity() {
        List<VideoCount> res = videoCountRepository.findAll();
        List<VideoCount> collect = res.stream().sorted(Comparator.comparing(VideoCount::getMetrics).reversed()).collect(Collectors.toList());

        return collect;
    }

//    @GetMapping("/delete")
//    public void deleteAllVideoCount() {
//        List<VideoCount> all = videoCountRepository.findAll();
//        List<VideoCount> res = all.stream().map(videoCount -> {
//            videoCount.setMaxVies(0);
//            videoCount.setCount(0);
//            videoCount.setMetrics(0L);
//            return videoCount;
//        }).collect(Collectors.toList());
//
//        videoCountRepository.saveAll(res);
//    }

    public Integer getMaxViews(VideoCount vc) {
        if (vc.getMaxVies() == null)
            return 0;
        return vc.getMaxVies();
    }


}
