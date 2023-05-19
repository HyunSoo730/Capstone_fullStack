package merge.capstone.fullstack.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

//@Entity
@Getter @Setter
public class Facility {

    @Id
    @Column(name = "facility_id")
    private Long id;

    private Integer year;
    private Integer quarter;
    private Integer commercial_code;

    private Integer num_of_facility;
    private Integer num_of_accommodation;
    private Integer num_of_airport;
    private Integer num_of_bank;
    private Integer num_of_bus_stop;
    private Integer num_of_bus_terminal;
    private Integer num_of_department_store;
    private Integer num_of_elementary_school;
    private Integer num_of_general_hospital;
    private Integer num_of_government_office;
    private Integer num_of_high_school;
    private Integer num_of_hospital;
    private Integer num_of_kindergarten;
    private Integer num_of_middle_school;
    private Integer num_of_pharmacy;
    private Integer num_of_rail_station;
    private Integer num_of_subway;
    private Integer num_of_supermarket;
    private Integer num_of_theater;
    private Integer num_of_university;
}
