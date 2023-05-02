package capstone.fullstack.service.youtube;

import capstone.fullstack.domain.youtube.Youtube;
import capstone.fullstack.repository.youtube.YoutubeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class YoutubeService {

    private final YoutubeRepository youtubeRepository;

    public List<Youtube> findAllByDong(String dong) {
        List<Youtube> all = youtubeRepository.findByDong(dong);
        return all;
    }

}
