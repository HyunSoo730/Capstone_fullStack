package capstone.fullstack.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRentalFee is a Querydsl query type for RentalFee
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRentalFee extends EntityPathBase<RentalFee> {

    private static final long serialVersionUID = -2136448536L;

    public static final QRentalFee rentalFee = new QRentalFee("rentalFee");

    public final StringPath area_name = createString("area_name");

    public final StringPath gu_name = createString("gu_name");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> quarter = createNumber("quarter", Integer.class);

    public final NumberPath<Integer> rentalfee_1st_floor = createNumber("rentalfee_1st_floor", Integer.class);

    public final NumberPath<Integer> rentalfee_except_1st_floor = createNumber("rentalfee_except_1st_floor", Integer.class);

    public final NumberPath<Integer> rentalfee_total = createNumber("rentalfee_total", Integer.class);

    public final NumberPath<Integer> year = createNumber("year", Integer.class);

    public QRentalFee(String variable) {
        super(RentalFee.class, forVariable(variable));
    }

    public QRentalFee(Path<? extends RentalFee> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRentalFee(PathMetadata metadata) {
        super(RentalFee.class, metadata);
    }

}

