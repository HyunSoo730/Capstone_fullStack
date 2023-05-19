package capstone.fullstack.repository;

import capstone.fullstack.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByKakaoEmail(String email);

    User findByUserId(Long id);
        
}
