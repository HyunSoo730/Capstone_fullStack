package merge.capstone.fullstack.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class WorkplacePopulation {

    @Id
    @Column(name = "workplace_id")
    private Long id;

    private Integer year;
    private Integer quarter;
    private Integer commercial_code;

    private Integer total_num_of_workplace;

    private Integer num_of10age_workplace;
    private Integer num_of20age_workplace;
    private Integer num_of30age_workplace;
    private Integer num_of40age_workplace;
    private Integer num_of50age_workplace;
    private Integer num_of60age_workplace;

    private Integer num_of_men_workplace;
    private Integer num_of10men_workplace;
    private Integer num_of20men_workplace;
    private Integer num_of30men_workplace;
    private Integer num_of40men_workplace;
    private Integer num_of50men_workplace;
    private Integer num_of60men_workplace;

    private Integer num_of_women_workplace;
    private Integer num_of10women_workplace;
    private Integer num_of20women_workplace;
    private Integer num_of30women_workplace;
    private Integer num_of40women_workplace;
    private Integer num_of50women_workplace;
    private Integer num_of60women_workplace;

}
