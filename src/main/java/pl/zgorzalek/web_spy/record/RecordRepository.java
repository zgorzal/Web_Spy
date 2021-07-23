package pl.zgorzalek.web_spy.record;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface RecordRepository extends JpaRepository<Record, Long> {
    List<Record> findAllByDownloadId(int downloadId);

    @Query(value = "SELECT MAX(download_id) FROM records", nativeQuery = true)
    int findLastDownloadId();
}
