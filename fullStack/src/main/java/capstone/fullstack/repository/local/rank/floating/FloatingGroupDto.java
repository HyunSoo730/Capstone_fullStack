package capstone.fullstack.repository.local.rank.floating;

import lombok.Data;

@Data
public class FloatingGroupDto {

    private int year;
    private String dong;
    private Long totalFlpop;


    public FloatingGroupDto(int year, String dong, Long totalFlpop) {
        this.year = year;
        this.dong = dong;
        this.totalFlpop = totalFlpop;
    }

}
