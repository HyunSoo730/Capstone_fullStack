package capstone.fullstack.domain.rank;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSalesGrowthRate is a Querydsl query type for SalesGrowthRate
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSalesGrowthRate extends EntityPathBase<SalesGrowthRate> {

    private static final long serialVersionUID = -282934901L;

    public static final QSalesGrowthRate salesGrowthRate = new QSalesGrowthRate("salesGrowthRate");

    public final StringPath dong = createString("dong");

    public final NumberPath<Double> growthRateFigures = createNumber("growthRateFigures", Double.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final NumberPath<Long> quarterFourTotal = createNumber("quarterFourTotal", Long.class);

    public final NumberPath<Long> quarterThreeTotal = createNumber("quarterThreeTotal", Long.class);

    public final NumberPath<Integer> ranking = createNumber("ranking", Integer.class);

    public final StringPath serviceName = createString("serviceName");

    public QSalesGrowthRate(String variable) {
        super(SalesGrowthRate.class, forVariable(variable));
    }

    public QSalesGrowthRate(Path<? extends SalesGrowthRate> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSalesGrowthRate(PathMetadata metadata) {
        super(SalesGrowthRate.class, metadata);
    }

}

