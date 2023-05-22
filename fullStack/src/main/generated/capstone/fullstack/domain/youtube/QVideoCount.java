package capstone.fullstack.domain.youtube;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QVideoCount is a Querydsl query type for VideoCount
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QVideoCount extends EntityPathBase<VideoCount> {

    private static final long serialVersionUID = 806333849L;

    public static final QVideoCount videoCount = new QVideoCount("videoCount");

    public final NumberPath<Integer> count = createNumber("count", Integer.class);

    public final StringPath dong = createString("dong");

    public final NumberPath<Integer> maxViews = createNumber("maxViews", Integer.class);

    public final NumberPath<Long> metrics = createNumber("metrics", Long.class);

    public QVideoCount(String variable) {
        super(VideoCount.class, forVariable(variable));
    }

    public QVideoCount(Path<? extends VideoCount> path) {
        super(path.getType(), path.getMetadata());
    }

    public QVideoCount(PathMetadata metadata) {
        super(VideoCount.class, metadata);
    }

}

