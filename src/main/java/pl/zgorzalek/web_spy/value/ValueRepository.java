package pl.zgorzalek.web_spy.value;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zgorzalek.web_spy.record.Record;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ValueRepository extends JpaRepository<Value, Long> {
    List<Value> findAllByRecord(Record record);
}
