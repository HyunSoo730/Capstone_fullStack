package capstone.fullstack.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCommerceChange is a Querydsl query type for CommerceChange
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCommerceChange extends EntityPathBase<CommerceChange> {

    private static final long serialVersionUID = -1868401659L;

    public static final QCommerceChange commerceChange = new QCommerceChange("commerceChange");

    public final StringPath commerceMetrics = createString("commerceMetrics");

    public final StringPath dong = createString("dong");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> quarter = createNumber("quarter", Integer.class);

    public final NumberPath<Integer> year = createNumber("year", Integer.class);

    public QCommerceChange(String variable) {
        super(CommerceChange.class, forVariable(variable));
    }

    public QCommerceChange(Path<? extends CommerceChange> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCommerceChange(PathMetadata metadata) {
        super(CommerceChange.class, metadata);
    }

}

