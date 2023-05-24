package capstone.fullstack.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLocal is a Querydsl query type for Local
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLocal extends EntityPathBase<Local> {

    private static final long serialVersionUID = -2068942703L;

    public static final QLocal local = new QLocal("local");

    public final NumberPath<Integer> area = createNumber("area", Integer.class);

    public final StringPath borough = createString("borough");

    public final NumberPath<Integer> commercialCode = createNumber("commercialCode", Integer.class);

    public final StringPath dong = createString("dong");

    public final NumberPath<Long> localId = createNumber("localId", Long.class);

    public QLocal(String variable) {
        super(Local.class, forVariable(variable));
    }

    public QLocal(Path<? extends Local> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLocal(PathMetadata metadata) {
        super(Local.class, metadata);
    }

}

