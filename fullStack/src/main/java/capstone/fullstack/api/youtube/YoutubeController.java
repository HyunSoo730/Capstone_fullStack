package capstone.fullstack.api.youtube;

import capstone.fullstack.domain.youtube.PopularVideo;
import capstone.fullstack.domain.youtube.VideoCount;
import capstone.fullstack.domain.youtube.Youtube;
import capstone.fullstack.repository.youtube.PopularVideoRepository;
import capstone.fullstack.repository.youtube.VideoCountRepository;
import capstone.fullstack.repository.youtube.YoutubeRepository;
import capstone.fullstack.service.youtube.YoutubeService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/youtube")
public class YoutubeController {

    private final YoutubeService youtubeService;
    private final YoutubeRepository youtubeRepository;
    private final VideoCountRepository videoCountRepository;

    //실시간 급상승 유튜브 영상을 위해
    private final PopularVideoRepository popularVideoRepository;


    /**
     * 프론트로부터 행정동 정보를 받으면 해당 행정동의 유튜브 엔티티 모두 반환.
     * 모든 정보 반환에서 -> top 10만 반환
     */
    @GetMapping("/find-entity/{dong}")
    public List<Youtube> findAllEntity(@PathVariable String dong) {
        List<Youtube> res = youtubeService.findAllByDong(dong);
        List<Youtube> temp = res.stream().sorted(Comparator.comparing(Youtube::getViews).reversed()).collect(Collectors.toList());
        List<Youtube> top10 = temp.subList(0, Math.min(10, temp.size()));
        return top10;
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
        //새로 추가 -> 태크 + 빈 컬럼 3개
        youtube.setTag(dto.getTag());
        youtube.setCol1(dto.getCol1());
        youtube.setCol2(dto.getCol2());
        youtube.setCol3(dto.getCol3());

        //DB에 넣을 유튜브 엔티티 생성 완료.
        youtubeRepository.save(youtube);  //DB에 저장
        //현재 유튜브 엔티티 저장까지는 됨.

        //이제 저장한 후에 해당 행정동의 영상 개수 갱신
        VideoCount videoCount = videoCountRepository.findById(dto.getDong()).get();
        videoCount.setCount(videoCount.getCount() + 1);  //영상 개수 반환 후.

        //영상 최대 조회수 갱신
        if (youtube.getViews() > getMaxViews(videoCount))
            videoCount.setMaxViews(youtube.getViews());
        // 지표값 갱신
        videoCount.setMetrics(Long.valueOf(videoCount.getCount() * videoCount.getMaxViews()));
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

    /**
     * 실시간 급상승 동영상. 행정동 파라미터로 받고 모든 정보 반환.
     */
    @GetMapping("/find-popular/{dong}")
    public List<PopularVideo> findPopular(@PathVariable String dong) {
        List<PopularVideo> res = popularVideoRepository.findByDong(dong)
                .stream().sorted(Comparator.comparing(PopularVideo::getViews).reversed())
                .collect(Collectors.toList());
        List<PopularVideo> top3 = res.subList(0, Math.min(3, res.size()));

        return top3;
    }


    /**
    @GetMapping("/test")
    public List<Youtube> findAfterDate() {
        List<Youtube> res = youtubeService.findSearch();
        List<PopularVideo> collect = res.stream().map(youtube -> {
            PopularVideo pv = new PopularVideo();
            pv.setDong(youtube.getDong());
            pv.setName(youtube.getName());
            pv.setViews(youtube.getViews());
            pv.setVideoLink(youtube.getVideoLink());
            pv.setLikes(youtube.getLikes());
            pv.setThumbnail(youtube.getThumbnail());
            pv.setDate(youtube.getDate());
            pv.setFood(youtube.getFood());
            pv.setTag(youtube.getTag());
            return pv;
        }).collect(Collectors.toList());

        popularVideoRepository.saveAll(collect);

        return res;
    }

    @Data
    static class Temp {
        HashMap<String, Integer> map;
        int count;
    }


    @GetMapping("/test2")
    public Temp check() {
        List<Youtube> all = youtubeService.findSearch();
        HashMap<String, Integer> resMap = new HashMap<>();
        for (Youtube youtube : all) {
            resMap.put(youtube.getDong(), resMap.getOrDefault(youtube.getDong(), 0) + 1);
        }

        Temp temp = new Temp();
        temp.setMap(resMap);
        temp.setCount(resMap.size());

        return temp;
    }
    **/

    public Integer getMaxViews(VideoCount vc) {

        if (vc.getMaxViews() == null)
            return 0;
        return vc.getMaxViews();
    }

}
