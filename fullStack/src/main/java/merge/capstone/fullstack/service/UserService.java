package merge.capstone.fullstack.service;

import merge.capstone.fullstack.controller.UserDTO;
import merge.capstone.fullstack.domain.User;
import merge.capstone.fullstack.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Long join(User user){
        userRepository.join(user);
        return user.getId();
    }

    @Transactional
    public Long update(Long id, UserDTO userParam) {
        if(userRepository.usableEmail(userParam.getEmail())) {
            userRepository.update(id, userParam);
        }
        return id;
    }

    public List<User> findUsers() {
        return userRepository.findMembers();
    }

    public List<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findById(Long id) {
        return userRepository.findById(id);
    }
}
