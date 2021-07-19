package pl.zgorzalek.web_spy.css;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CssRepository extends JpaRepository<Css, Long> {
}
