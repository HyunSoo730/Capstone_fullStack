package capstone.fullstack.domain.population;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QResident is a Querydsl query type for Resident
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QResident extends EntityPathBase<Resident> {

    private static final long serialVersionUID = 778036663L;

    public static final QResident resident = new QResident("resident");

    public final NumberPath<Integer> commercialCode = createNumber("commercialCode", Integer.class);

    public final NumberPath<Integer> numOfAge10Residents = createNumber("numOfAge10Residents", Integer.class);

    public final NumberPath<Integer> numOfAge20Residents = createNumber("numOfAge20Residents", Integer.class);

    public final NumberPath<Integer> numOfAge30Residents = createNumber("numOfAge30Residents", Integer.class);

    public final NumberPath<Integer> numOfAge40Residents = createNumber("numOfAge40Residents", Integer.class);

    public final NumberPath<Integer> numOfAge50Residents = createNumber("numOfAge50Residents", Integer.class);

    public final NumberPath<Integer> numOfAge60Residents = createNumber("numOfAge60Residents", Integer.class);

    public final NumberPath<Integer> numOfMen10Residents = createNumber("numOfMen10Residents", Integer.class);

    public final NumberPath<Integer> numOfMen20Residents = createNumber("numOfMen20Residents", Integer.class);

    public final NumberPath<Integer> numOfMen30Residents = createNumber("numOfMen30Residents", Integer.class);

    public final NumberPath<Integer> numOfMen40Residents = createNumber("numOfMen40Residents", Integer.class);

    public final NumberPath<Integer> numOfMen50Residents = createNumber("numOfMen50Residents", Integer.class);

    public final NumberPath<Integer> numOfMen60Residents = createNumber("numOfMen60Residents", Integer.class);

    public final NumberPath<Integer> numOfMenResidents = createNumber("numOfMenResidents", Integer.class);

    public final NumberPath<Integer> numOfWomen10Residents = createNumber("numOfWomen10Residents", Integer.class);

    public final NumberPath<Integer> numOfWomen20Residents = createNumber("numOfWomen20Residents", Integer.class);

    public final NumberPath<Integer> numOfWomen30Residents = createNumber("numOfWomen30Residents", Integer.class);

    public final NumberPath<Integer> numOfWomen40Residents = createNumber("numOfWomen40Residents", Integer.class);

    public final NumberPath<Integer> numOfWomen50Residents = createNumber("numOfWomen50Residents", Integer.class);

    public final NumberPath<Integer> numOfWomen60Residents = createNumber("numOfWomen60Residents", Integer.class);

    public final NumberPath<Integer> numOfWomenResidents = createNumber("numOfWomenResidents", Integer.class);

    public final NumberPath<Integer> quarter = createNumber("quarter", Integer.class);

    public final NumberPath<Long> residentId = createNumber("residentId", Long.class);

    public final NumberPath<Integer> totalNumOfResidents = createNumber("totalNumOfResidents", Integer.class);

    public final NumberPath<Integer> year = createNumber("year", Integer.class);

    public QResident(String variable) {
        super(Resident.class, forVariable(variable));
    }

    public QResident(Path<? extends Resident> path) {
        super(path.getType(), path.getMetadata());
    }

    public QResident(PathMetadata metadata) {
        super(Resident.class, metadata);
    }

}

