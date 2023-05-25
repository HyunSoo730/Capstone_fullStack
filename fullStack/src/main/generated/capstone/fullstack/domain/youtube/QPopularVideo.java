package capstone.fullstack.domain.youtube;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPopularVideo is a Querydsl query type for PopularVideo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPopularVideo extends EntityPathBase<PopularVideo> {

    private static final long serialVersionUID = 1887690791L;

    public static final QPopularVideo popularVideo = new QPopularVideo("popularVideo");

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

    public QPopularVideo(String variable) {
        super(PopularVideo.class, forVariable(variable));
    }

    public QPopularVideo(Path<? extends PopularVideo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPopularVideo(PathMetadata metadata) {
        super(PopularVideo.class, metadata);
    }

}

