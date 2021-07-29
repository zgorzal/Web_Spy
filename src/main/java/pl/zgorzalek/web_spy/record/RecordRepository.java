package pl.zgorzalek.web_spy.record;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.zgorzalek.web_spy.css.Css;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface RecordRepository extends JpaRepository<Record, Long> {
    List<Record> findAllByDownloadId(int downloadId);

    @Query(value = "SELECT MAX(download_id) FROM records", nativeQuery = true)
    Optional<Integer> findLastDownloadId();

    @Query(value = "SELECT DISTINCT download_id FROM records WHERE css_id = ?1", nativeQuery = true)
    List<Integer> findDownloadIdByCss(Long css);

    Record findFirstByDownloadId(Integer downloadId);

    List<Record> findAllByCss(Css css);
}
