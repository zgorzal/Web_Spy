package pl.zgorzalek.web_spy.page;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface PageRepository extends JpaRepository<Page, Long> {
}
