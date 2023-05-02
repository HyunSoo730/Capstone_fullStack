package merge.capstone.fullstack.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

//@Entity
@Getter @Setter
public class IndustryAnalysis {

    @Id
    @Column(name = "industry_id")
    private Long id;

    private Integer year;
    private Integer quarter;
    private Integer commercial_code;
    private String service_name;

    private Integer num_of_stores;
    private Integer num_of_franchise_stores;
    private Integer total_num_of_stores;
    private Integer num_of_close_stores;
    private Integer num_of_open_stores;


}
