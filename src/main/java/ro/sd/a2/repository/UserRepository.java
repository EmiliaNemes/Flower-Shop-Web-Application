package ro.sd.a2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.sd.a2.entity.Role;
import ro.sd.a2.entity.User;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, String> {

    User findByUsername(String username);
    User findByUsernameAndPassword(String username, String password);
    List<User> findByRole(Role role);
}
