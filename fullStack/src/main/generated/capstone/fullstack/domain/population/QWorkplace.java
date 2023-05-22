package capstone.fullstack.domain.population;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QWorkplace is a Querydsl query type for Workplace
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWorkplace extends EntityPathBase<Workplace> {

    private static final long serialVersionUID = 1626229007L;

    public static final QWorkplace workplace = new QWorkplace("workplace");

    public final NumberPath<Integer> commercialCode = createNumber("commercialCode", Integer.class);

    public final NumberPath<Integer> numOf10AgeWorkplace = createNumber("numOf10AgeWorkplace", Integer.class);

    public final NumberPath<Integer> numOf10MenWorkplace = createNumber("numOf10MenWorkplace", Integer.class);

    public final NumberPath<Integer> numOf10WomenWorkplace = createNumber("numOf10WomenWorkplace", Integer.class);

    public final NumberPath<Integer> numOf20AgeWorkplace = createNumber("numOf20AgeWorkplace", Integer.class);

    public final NumberPath<Integer> numOf20MenWorkplace = createNumber("numOf20MenWorkplace", Integer.class);

    public final NumberPath<Integer> numOf20WomenWorkplace = createNumber("numOf20WomenWorkplace", Integer.class);

    public final NumberPath<Integer> numOf30AgeWorkplace = createNumber("numOf30AgeWorkplace", Integer.class);

    public final NumberPath<Integer> numOf30MenWorkplace = createNumber("numOf30MenWorkplace", Integer.class);

    public final NumberPath<Integer> numOf30WomenWorkplace = createNumber("numOf30WomenWorkplace", Integer.class);

    public final NumberPath<Integer> numOf40AgeWorkplace = createNumber("numOf40AgeWorkplace", Integer.class);

    public final NumberPath<Integer> numOf40MenWorkplace = createNumber("numOf40MenWorkplace", Integer.class);

    public final NumberPath<Integer> numOf40WomenWorkplace = createNumber("numOf40WomenWorkplace", Integer.class);

    public final NumberPath<Integer> numOf50AgeWorkplace = createNumber("numOf50AgeWorkplace", Integer.class);

    public final NumberPath<Integer> numOf50MenWorkplace = createNumber("numOf50MenWorkplace", Integer.class);

    public final NumberPath<Integer> numOf50WomenWorkplace = createNumber("numOf50WomenWorkplace", Integer.class);

    public final NumberPath<Integer> numOf60AgeWorkplace = createNumber("numOf60AgeWorkplace", Integer.class);

    public final NumberPath<Integer> numOf60MenWorkplace = createNumber("numOf60MenWorkplace", Integer.class);

    public final NumberPath<Integer> numOf60WomenWorkplace = createNumber("numOf60WomenWorkplace", Integer.class);

    public final NumberPath<Integer> numOfMenWorkplace = createNumber("numOfMenWorkplace", Integer.class);

    public final NumberPath<Integer> numOfWomenWorkplace = createNumber("numOfWomenWorkplace", Integer.class);

    public final NumberPath<Integer> quarter = createNumber("quarter", Integer.class);

    public final NumberPath<Integer> totalNumOfWorkplace = createNumber("totalNumOfWorkplace", Integer.class);

    public final NumberPath<Long> workplaceId = createNumber("workplaceId", Long.class);

    public final NumberPath<Integer> year = createNumber("year", Integer.class);

    public QWorkplace(String variable) {
        super(Workplace.class, forVariable(variable));
    }

    public QWorkplace(Path<? extends Workplace> path) {
        super(path.getType(), path.getMetadata());
    }

    public QWorkplace(PathMetadata metadata) {
        super(Workplace.class, metadata);
    }

}

