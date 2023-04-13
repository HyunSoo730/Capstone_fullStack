package capstone.fullstack.repository;

import capstone.fullstack.domain.Member;
import capstone.fullstack.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface MemberRepository extends JpaRepository<User, Long> {
    //select m from Member m where m.email = ? JPQL 자동 생성
    User findByKakaoEmail(String email);

    User findByUserId(Long id);


//    Member findByEmailAndPassword(String email, String password);
}
