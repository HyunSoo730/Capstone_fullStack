package capstone.fullstack.repository.local.commerce;

import capstone.fullstack.domain.CommerceChange;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommerceChangeRepository extends JpaRepository<CommerceChange, Long> {

    List<CommerceChange> findByDong(String dong);

}
