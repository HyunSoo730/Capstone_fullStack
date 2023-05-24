package capstone.fullstack.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAvgOperationPeriod is a Querydsl query type for AvgOperationPeriod
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAvgOperationPeriod extends EntityPathBase<AvgOperationPeriod> {

    private static final long serialVersionUID = 1111378288L;

    public static final QAvgOperationPeriod avgOperationPeriod = new QAvgOperationPeriod("avgOperationPeriod");

    public final StringPath area_name = createString("area_name");

    public final NumberPath<Double> avg_period = createNumber("avg_period", Double.class);

    public final StringPath gu_name = createString("gu_name");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> quarter = createNumber("quarter", Integer.class);

    public final StringPath service_name = createString("service_name");

    public final NumberPath<Integer> year = createNumber("year", Integer.class);

    public QAvgOperationPeriod(String variable) {
        super(AvgOperationPeriod.class, forVariable(variable));
    }

    public QAvgOperationPeriod(Path<? extends AvgOperationPeriod> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAvgOperationPeriod(PathMetadata metadata) {
        super(AvgOperationPeriod.class, metadata);
    }

}

