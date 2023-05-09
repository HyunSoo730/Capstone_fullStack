package capstone.fullstack.domain.rank;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter @Getter
@NoArgsConstructor
public class FloatingRentalFeeRank implements Comparable<FloatingRentalFeeRank>{

    @Id
    private int ranking;
    private String areaName;
    private float value;

    public FloatingRentalFeeRank(String areaName, float value) {
        this.areaName = areaName;
        this.value = value;
    }

    @Override
    public int compareTo(FloatingRentalFeeRank o) {
        return Float.compare(value, o.getValue());
    }
}
