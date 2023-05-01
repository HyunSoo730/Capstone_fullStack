package merge.capstone.fullstack.repository;

import merge.capstone.fullstack.controller.UserDTO;
import merge.capstone.fullstack.domain.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    public void join(User user) {
        em.persist(user);
    }

    public void update(Long id, UserDTO userParam) {
        User findUser = findById(id);
        findUser.setEmail(userParam.getEmail());
        findUser.setNickname(userParam.getNickname());
        findUser.setWishArea(userParam.getWishArea());
    }

    public User findById(Long id) {
        return em.find(User.class, id);
     }

    public boolean usableEmail(String email) {
        List<User> emailUser = findByEmail(email);
        if(emailUser.size() == 0){
            return true;
        }
        return false;
    }

    public List<User> findMembers() {
        return em.createQuery("select u from User u", User.class).getResultList();
    }

    public List<User> findByEmail(String email) {
        return em.createQuery("select u from User u WHERE u.email = :email", User.class)
                .setParameter("email", email).getResultList();
    }

    public List<User> findByName(String name) {
        return em.createQuery("select u from User u WHERE u.nickname = :name", User.class)
                .setParameter("name", name).getResultList();
    }
}
