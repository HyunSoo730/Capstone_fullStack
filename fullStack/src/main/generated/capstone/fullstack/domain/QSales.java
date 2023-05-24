package capstone.fullstack.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QSales is a Querydsl query type for Sales
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSales extends EntityPathBase<Sales> {

    private static final long serialVersionUID = -2062886350L;

    public static final QSales sales = new QSales("sales");

    public final NumberPath<Long> age10Sales = createNumber("age10Sales", Long.class);

    public final NumberPath<Long> age20Sales = createNumber("age20Sales", Long.class);

    public final NumberPath<Long> age30Sales = createNumber("age30Sales", Long.class);

    public final NumberPath<Long> age40Sales = createNumber("age40Sales", Long.class);

    public final NumberPath<Long> age50Sales = createNumber("age50Sales", Long.class);

    public final NumberPath<Long> age60Sales = createNumber("age60Sales", Long.class);

    public final NumberPath<Integer> commercialCode = createNumber("commercialCode", Integer.class);

    public final StringPath dong = createString("dong");

    public final NumberPath<Long> femaleSales = createNumber("femaleSales", Long.class);

    public final NumberPath<Long> friSales = createNumber("friSales", Long.class);

    public final NumberPath<Long> maleSales = createNumber("maleSales", Long.class);

    public final NumberPath<Long> monSales = createNumber("monSales", Long.class);

    public final NumberPath<Long> numOfStores = createNumber("numOfStores", Long.class);

    public final NumberPath<Integer> quarter = createNumber("quarter", Integer.class);

    public final NumberPath<Long> salesId = createNumber("salesId", Long.class);

    public final NumberPath<Long> salesPerQuarter = createNumber("salesPerQuarter", Long.class);

    public final NumberPath<Long> satSales = createNumber("satSales", Long.class);

    public final StringPath serviceName = createString("serviceName");

    public final NumberPath<Long> sunSales = createNumber("sunSales", Long.class);

    public final NumberPath<Long> thuSales = createNumber("thuSales", Long.class);

    public final NumberPath<Long> time1Sales = createNumber("time1Sales", Long.class);

    public final NumberPath<Long> time2Sales = createNumber("time2Sales", Long.class);

    public final NumberPath<Long> time3Sales = createNumber("time3Sales", Long.class);

    public final NumberPath<Long> time4Sales = createNumber("time4Sales", Long.class);

    public final NumberPath<Long> time5Sales = createNumber("time5Sales", Long.class);

    public final NumberPath<Long> time6Sales = createNumber("time6Sales", Long.class);

    public final NumberPath<Long> tueSales = createNumber("tueSales", Long.class);

    public final NumberPath<Long> wedSales = createNumber("wedSales", Long.class);

    public final NumberPath<Long> weekendSales = createNumber("weekendSales", Long.class);

    public final NumberPath<Long> weeklySales = createNumber("weeklySales", Long.class);

    public final NumberPath<Integer> year = createNumber("year", Integer.class);

    public QSales(String variable) {
        super(Sales.class, forVariable(variable));
    }

    public QSales(Path<? extends Sales> path) {
        super(path.getType(), path.getMetadata());
    }

    public QSales(PathMetadata metadata) {
        super(Sales.class, metadata);
    }

}

