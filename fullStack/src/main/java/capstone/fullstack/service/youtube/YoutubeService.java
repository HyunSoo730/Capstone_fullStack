package capstone.fullstack.service.youtube;

import capstone.fullstack.domain.youtube.Youtube;
import capstone.fullstack.repository.youtube.YoutubeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class YoutubeService {

    private final YoutubeRepository youtubeRepository;

    public List<Youtube> findAllByDong(String dong) {
        List<Youtube> all = youtubeRepository.findByDong(dong);
        return all;
    }

    public List<Youtube> findSearch() {
        List<Youtube> all = youtubeRepository.findAll();
        List<Youtube> res = all.stream().filter(youtube -> youtube.getDate().isAfter(LocalDate.of(2023, 5, 1))).collect(Collectors.toList());
        return res;
    }

}
