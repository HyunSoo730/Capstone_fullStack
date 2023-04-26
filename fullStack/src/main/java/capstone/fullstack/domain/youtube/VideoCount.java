package capstone.fullstack.domain.youtube;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class VideoCount {

    @Id
    private String dong;   //동으로 구분

    private Integer count;

}
