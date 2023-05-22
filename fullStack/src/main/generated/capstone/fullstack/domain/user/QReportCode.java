package capstone.fullstack.domain.user;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QReportCode is a Querydsl query type for ReportCode
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReportCode extends EntityPathBase<ReportCode> {

    private static final long serialVersionUID = -1931595478L;

    public static final QReportCode reportCode = new QReportCode("reportCode");

    public final StringPath borough = createString("borough");

    public final StringPath dong = createString("dong");

    public final NumberPath<Long> reportId = createNumber("reportId", Long.class);

    public final StringPath serviceName = createString("serviceName");

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QReportCode(String variable) {
        super(ReportCode.class, forVariable(variable));
    }

    public QReportCode(Path<? extends ReportCode> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReportCode(PathMetadata metadata) {
        super(ReportCode.class, metadata);
    }

}

