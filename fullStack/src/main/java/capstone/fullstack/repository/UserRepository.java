package capstone.fullstack.repository;

import capstone.fullstack.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByKakaoEmail(String email);

    User findByUserId(Long id);



}
