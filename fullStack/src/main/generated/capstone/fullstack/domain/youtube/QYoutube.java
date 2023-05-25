package capstone.fullstack.domain.youtube;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QYoutube is a Querydsl query type for Youtube
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QYoutube extends EntityPathBase<Youtube> {

    private static final long serialVersionUID = -1684255490L;

    public static final QYoutube youtube = new QYoutube("youtube");

    public final StringPath col1 = createString("col1");

    public final StringPath col2 = createString("col2");

    public final StringPath col3 = createString("col3");

    public final DatePath<java.time.LocalDate> date = createDate("date", java.time.LocalDate.class);

    public final StringPath dong = createString("dong");

    public final StringPath food = createString("food");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> likes = createNumber("likes", Integer.class);

    public final StringPath name = createString("name");

    public final StringPath tag = createString("tag");

    public final StringPath thumbnail = createString("thumbnail");

    public final StringPath videoLink = createString("videoLink");

    public final NumberPath<Integer> views = createNumber("views", Integer.class);

    public QYoutube(String variable) {
        super(Youtube.class, forVariable(variable));
    }

    public QYoutube(Path<? extends Youtube> path) {
        super(path.getType(), path.getMetadata());
    }

    public QYoutube(PathMetadata metadata) {
        super(Youtube.class, metadata);
    }

}

