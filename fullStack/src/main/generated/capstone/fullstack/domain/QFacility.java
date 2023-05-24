package capstone.fullstack.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFacility is a Querydsl query type for Facility
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFacility extends EntityPathBase<Facility> {

    private static final long serialVersionUID = -195984451L;

    public static final QFacility facility = new QFacility("facility");

    public final NumberPath<Integer> commercialCode = createNumber("commercialCode", Integer.class);

    public final NumberPath<Long> facilityId = createNumber("facilityId", Long.class);

    public final NumberPath<Integer> numOfAccommodation = createNumber("numOfAccommodation", Integer.class);

    public final NumberPath<Integer> numOfAirport = createNumber("numOfAirport", Integer.class);

    public final NumberPath<Integer> numOfBank = createNumber("numOfBank", Integer.class);

    public final NumberPath<Integer> numOfBusStop = createNumber("numOfBusStop", Integer.class);

    public final NumberPath<Integer> numOfBusTerminal = createNumber("numOfBusTerminal", Integer.class);

    public final NumberPath<Integer> numOfDepartmentStore = createNumber("numOfDepartmentStore", Integer.class);

    public final NumberPath<Integer> numOfElementarySchool = createNumber("numOfElementarySchool", Integer.class);

    public final NumberPath<Integer> numOfFacility = createNumber("numOfFacility", Integer.class);

    public final NumberPath<Integer> numOfGeneralHospital = createNumber("numOfGeneralHospital", Integer.class);

    public final NumberPath<Integer> numOfGovernmentOffice = createNumber("numOfGovernmentOffice", Integer.class);

    public final NumberPath<Integer> numOfHighSchool = createNumber("numOfHighSchool", Integer.class);

    public final NumberPath<Integer> numOfHospital = createNumber("numOfHospital", Integer.class);

    public final NumberPath<Integer> numOfKindergarten = createNumber("numOfKindergarten", Integer.class);

    public final NumberPath<Integer> numOfMiddleSchool = createNumber("numOfMiddleSchool", Integer.class);

    public final NumberPath<Integer> numOfPharmacy = createNumber("numOfPharmacy", Integer.class);

    public final NumberPath<Integer> numOfRailStation = createNumber("numOfRailStation", Integer.class);

    public final NumberPath<Integer> numOfSubway = createNumber("numOfSubway", Integer.class);

    public final NumberPath<Integer> numOfSupermarket = createNumber("numOfSupermarket", Integer.class);

    public final NumberPath<Integer> numOfTheater = createNumber("numOfTheater", Integer.class);

    public final NumberPath<Integer> numOfUniversity = createNumber("numOfUniversity", Integer.class);

    public final NumberPath<Integer> quarter = createNumber("quarter", Integer.class);

    public final NumberPath<Integer> year = createNumber("year", Integer.class);

    public QFacility(String variable) {
        super(Facility.class, forVariable(variable));
    }

    public QFacility(Path<? extends Facility> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFacility(PathMetadata metadata) {
        super(Facility.class, metadata);
    }

}

