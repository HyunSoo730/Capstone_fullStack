package merge.capstone.fullstack.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

//@Entity
@Getter @Setter
public class Local {

    @Id
    @Column(name = "local_id")
    private Long id;

    private Integer area;
    private String borough;

    private Integer commercial_code;
    private String dong;
}
