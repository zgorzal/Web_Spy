package pl.zgorzalek.web_spy.page;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zgorzalek.web_spy.user.User;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface PageRepository extends JpaRepository<Page, Long> {
    List<Page> findPagesByUser(User user);
}
