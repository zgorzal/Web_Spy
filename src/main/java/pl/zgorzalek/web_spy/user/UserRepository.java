package pl.zgorzalek.web_spy.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.verificationCode = ?1")
    User findByVerificationCode(String code);
}
