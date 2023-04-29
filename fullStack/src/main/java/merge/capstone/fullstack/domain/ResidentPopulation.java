package merge.capstone.fullstack.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class ResidentPopulation {

    @Id
    @Column(name = "resident_id")
    private Long id;

    private Integer year;
    private Integer quarter;
    private Integer commercial_code;

    private Integer total_num_of_residents;

    private Integer num_of_age10residents;
    private Integer num_of_age20residents;
    private Integer num_of_age30residents;
    private Integer num_of_age40residents;
    private Integer num_of_age50residents;
    private Integer num_of_age60residents;

    private Integer num_of_men_residents;
    private Integer num_of_men10residents;
    private Integer num_of_men20residents;
    private Integer num_of_men30residents;
    private Integer num_of_men40residents;
    private Integer num_of_men50residents;
    private Integer num_of_men60residents;

    private Integer num_of_women_residents;
    private Integer num_of_women10residents;
    private Integer num_of_women20residents;
    private Integer num_of_women30residents;
    private Integer num_of_women40residents;
    private Integer num_of_women50residents;
    private Integer num_of_women60residents;

}
