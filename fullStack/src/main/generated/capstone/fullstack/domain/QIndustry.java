package capstone.fullstack.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QIndustry is a Querydsl query type for Industry
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QIndustry extends EntityPathBase<Industry> {

    private static final long serialVersionUID = -569944328L;

    public static final QIndustry industry = new QIndustry("industry");

    public final NumberPath<Integer> commercialCode = createNumber("commercialCode", Integer.class);

    public final NumberPath<Long> industryId = createNumber("industryId", Long.class);

    public final NumberPath<Integer> numOfCloseStores = createNumber("numOfCloseStores", Integer.class);

    public final NumberPath<Integer> numOfFranchiseStores = createNumber("numOfFranchiseStores", Integer.class);

    public final NumberPath<Integer> numOfOpenStores = createNumber("numOfOpenStores", Integer.class);

    public final NumberPath<Integer> numOfStores = createNumber("numOfStores", Integer.class);

    public final NumberPath<Integer> quarter = createNumber("quarter", Integer.class);

    public final StringPath serviceName = createString("serviceName");

    public final NumberPath<Integer> totalNumOfStores = createNumber("totalNumOfStores", Integer.class);

    public final NumberPath<Integer> year = createNumber("year", Integer.class);

    public QIndustry(String variable) {
        super(Industry.class, forVariable(variable));
    }

    public QIndustry(Path<? extends Industry> path) {
        super(path.getType(), path.getMetadata());
    }

    public QIndustry(PathMetadata metadata) {
        super(Industry.class, metadata);
    }

}

