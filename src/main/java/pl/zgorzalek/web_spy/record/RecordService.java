package pl.zgorzalek.web_spy.record;

import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;
import pl.zgorzalek.web_spy.css.Css;
import pl.zgorzalek.web_spy.css.CssService;
import pl.zgorzalek.web_spy.page.Page;
import pl.zgorzalek.web_spy.page.PageService;
import pl.zgorzalek.web_spy.value.Value;
import pl.zgorzalek.web_spy.value.ValueService;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RecordService {
    private final RecordRepository recordRepository;
    private final CssService cssService;
    private final PageService pageService;
    private final ValueService valueService;

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

    public List<Record> getRecordSummary(Long pageId, String cssClass) {
        Page page = pageService.findById(pageId);
        Css css = cssService.findByName(cssClass);
        if (css == null) {
            css = new Css();
        }
        int downloadId = getLastDownloadId() + 1;
        css.setName(cssClass);
        css.setPage(page);
        cssService.add(css);
        try {
            Document document = Jsoup.connect(page.getUrl()).get();
            Element body = document.body();
            List<Element> parentElements = body.getElementsByClass(cssClass);
            for (Element parentElement : parentElements) {
                Record record = new Record();
                record.setCss(css);
                record.setDownloadId(downloadId);
                add(record);
                List<Element> childrenElements = parentElement.getAllElements();
                for (Element childElement : childrenElements) {
                    if (!childElement.ownText().isEmpty()) {
                        Value value = new Value();
                        value.setRecord(record);
                        value.setValue(childElement.ownText());
                        valueService.add(value);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Record> records = getAllByDownloadId(downloadId);
        for (Record record : records) {
            record.setValues(valueService.getAllByRecord(record));
        }
        return records;
    }
}
