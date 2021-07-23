package pl.zgorzalek.web_spy.value;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zgorzalek.web_spy.record.Record;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ValueService {
    private final ValueRepository valueRepository;

    public void add(Value value) {
        valueRepository.save(value);
    }

    public List<Value> getAllByRecord(Record record) {
        return valueRepository.findAllByRecord(record);
    }
}
