package capstone.fullstack.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QIncomeConsumption is a Querydsl query type for IncomeConsumption
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QIncomeConsumption extends EntityPathBase<IncomeConsumption> {

    private static final long serialVersionUID = 163810904L;

    public static final QIncomeConsumption incomeConsumption = new QIncomeConsumption("incomeConsumption");

    public final NumberPath<Long> averageMonthlyIncome = createNumber("averageMonthlyIncome", Long.class);

    public final NumberPath<Integer> commercialCode = createNumber("commercialCode", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> quarter = createNumber("quarter", Integer.class);

    public final NumberPath<Long> totalAmountSpent = createNumber("totalAmountSpent", Long.class);

    public final NumberPath<Integer> year = createNumber("year", Integer.class);

    public QIncomeConsumption(String variable) {
        super(IncomeConsumption.class, forVariable(variable));
    }

    public QIncomeConsumption(Path<? extends IncomeConsumption> path) {
        super(path.getType(), path.getMetadata());
    }

    public QIncomeConsumption(PathMetadata metadata) {
        super(IncomeConsumption.class, metadata);
    }

}

