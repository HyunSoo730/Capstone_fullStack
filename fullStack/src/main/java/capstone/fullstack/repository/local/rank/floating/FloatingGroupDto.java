package capstone.fullstack.repository.local.rank.floating;

import lombok.Data;

@Data
public class FloatingGroupDto {

    private int year;
    private String dong;
    private Long totalFlpop;

    private Long count;  //분기수 * 상권수

    public FloatingGroupDto(int year, String dong, Long totalFlpop, Long count) {
        this.year = year;
        this.dong = dong;
        this.totalFlpop = totalFlpop;
        this.count = count;
    }

}
