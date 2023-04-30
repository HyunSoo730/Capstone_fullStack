package merge.capstone.fullstack.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class CommerceChange {

    @Id
    private Long id;

    private Integer year;
    private Integer quarter;
    private String dong;
    private String commerce_metrics;

}
