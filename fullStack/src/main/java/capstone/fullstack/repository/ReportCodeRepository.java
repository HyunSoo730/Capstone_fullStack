package capstone.fullstack.repository;

import capstone.fullstack.domain.user.ReportCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReportCodeRepository extends JpaRepository<ReportCode, Long> {
    List<ReportCode> findByUserId(Long id);

    void deleteByUserId(Long id);
}
