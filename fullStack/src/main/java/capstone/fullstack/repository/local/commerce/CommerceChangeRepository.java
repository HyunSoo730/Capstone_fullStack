package capstone.fullstack.repository.local.commerce;

import capstone.fullstack.domain.CommerceChange;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CommerceChangeRepository extends JpaRepository<CommerceChange, Long> {

    List<CommerceChange> findByDong(String dong);

}
