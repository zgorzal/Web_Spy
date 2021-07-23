package pl.zgorzalek.web_spy.record;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RecordService {
    private final RecordRepository recordRepository;

    public void add(Record record) {
        record.setDateAdded(LocalDateTime.now());
        recordRepository.save(record);
    }

    public List<Record> getAllByDownloadId(int id) {
        return recordRepository.findAllByDownloadId(id);
    }

    public int getLastDownloadId() {
        return recordRepository.findLastDownloadId();
    }

}
