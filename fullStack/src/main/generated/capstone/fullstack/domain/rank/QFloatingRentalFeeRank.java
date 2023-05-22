package capstone.fullstack.domain.rank;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFloatingRentalFeeRank is a Querydsl query type for FloatingRentalFeeRank
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFloatingRentalFeeRank extends EntityPathBase<FloatingRentalFeeRank> {

    private static final long serialVersionUID = 1573807808L;

    public static final QFloatingRentalFeeRank floatingRentalFeeRank = new QFloatingRentalFeeRank("floatingRentalFeeRank");

    public final StringPath areaName = createString("areaName");

    public final NumberPath<Long> floating = createNumber("floating", Long.class);

    public final NumberPath<Integer> ranking = createNumber("ranking", Integer.class);

    public final NumberPath<Integer> rentalFee = createNumber("rentalFee", Integer.class);

    public final NumberPath<Float> value = createNumber("value", Float.class);

    public QFloatingRentalFeeRank(String variable) {
        super(FloatingRentalFeeRank.class, forVariable(variable));
    }

    public QFloatingRentalFeeRank(Path<? extends FloatingRentalFeeRank> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFloatingRentalFeeRank(PathMetadata metadata) {
        super(FloatingRentalFeeRank.class, metadata);
    }

}

