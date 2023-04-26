package capstone.fullstack.repository.local.commerce;

import capstone.fullstack.domain.Local;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocalRepository extends JpaRepository<Local, Long> {
    List<Local> findByDong(String dong);  //특정 행정동에 대한 Local 전부 꺼내기

    Local findFirstByCommercialCode(Integer code);

}
