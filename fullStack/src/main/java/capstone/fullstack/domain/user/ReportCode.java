package capstone.fullstack.domain.user;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class ReportCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reportId;

    private Long userId;

    private String borough;  //자치구
    private String dong;   //행정동
    private String serviceName;


    public ReportCode() {

    }
}
