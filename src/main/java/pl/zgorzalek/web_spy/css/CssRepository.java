package pl.zgorzalek.web_spy.css;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zgorzalek.web_spy.page.Page;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface CssRepository extends JpaRepository<Css, Long> {
    Css findFirstByName(String name);

    List<Css> findAllByPage(Page page);
}
