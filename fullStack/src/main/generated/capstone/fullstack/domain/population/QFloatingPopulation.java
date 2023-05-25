package capstone.fullstack.domain.population;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFloatingPopulation is a Querydsl query type for FloatingPopulation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFloatingPopulation extends EntityPathBase<FloatingPopulation> {

    private static final long serialVersionUID = -164787302L;

    public static final QFloatingPopulation floatingPopulation = new QFloatingPopulation("floatingPopulation");

    public final NumberPath<Integer> age_10_flpop = createNumber("age_10_flpop", Integer.class);

    public final NumberPath<Integer> age_20_flpop = createNumber("age_20_flpop", Integer.class);

    public final NumberPath<Integer> age_30_flpop = createNumber("age_30_flpop", Integer.class);

    public final NumberPath<Integer> age_40_flpop = createNumber("age_40_flpop", Integer.class);

    public final NumberPath<Integer> age_50_flpop = createNumber("age_50_flpop", Integer.class);

    public final NumberPath<Integer> age_60_flpop = createNumber("age_60_flpop", Integer.class);

    public final NumberPath<Integer> commercial_code = createNumber("commercial_code", Integer.class);

    public final NumberPath<Integer> female_flpop = createNumber("female_flpop", Integer.class);

    public final NumberPath<Integer> fri_flpop = createNumber("fri_flpop", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> male_flpop = createNumber("male_flpop", Integer.class);

    public final NumberPath<Integer> mon_flpop = createNumber("mon_flpop", Integer.class);

    public final NumberPath<Integer> quarter = createNumber("quarter", Integer.class);

    public final NumberPath<Integer> sat_flpop = createNumber("sat_flpop", Integer.class);

    public final NumberPath<Integer> sun_flpop = createNumber("sun_flpop", Integer.class);

    public final NumberPath<Integer> thu_flpop = createNumber("thu_flpop", Integer.class);

    public final NumberPath<Integer> time_1_flpop = createNumber("time_1_flpop", Integer.class);

    public final NumberPath<Integer> time_2_flpop = createNumber("time_2_flpop", Integer.class);

    public final NumberPath<Integer> time_3_flpop = createNumber("time_3_flpop", Integer.class);

    public final NumberPath<Integer> time_4_flpop = createNumber("time_4_flpop", Integer.class);

    public final NumberPath<Integer> time_5_flpop = createNumber("time_5_flpop", Integer.class);

    public final NumberPath<Integer> time_6_flpop = createNumber("time_6_flpop", Integer.class);

    public final NumberPath<Integer> total_flpop = createNumber("total_flpop", Integer.class);

    public final NumberPath<Integer> tue_flpop = createNumber("tue_flpop", Integer.class);

    public final NumberPath<Integer> wed_flpop = createNumber("wed_flpop", Integer.class);

    public final NumberPath<Integer> year = createNumber("year", Integer.class);

    public QFloatingPopulation(String variable) {
        super(FloatingPopulation.class, forVariable(variable));
    }

    public QFloatingPopulation(Path<? extends FloatingPopulation> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFloatingPopulation(PathMetadata metadata) {
        super(FloatingPopulation.class, metadata);
    }

}

