package pl.zgorzalek.web_spy.record;

import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;
import pl.zgorzalek.web_spy.css.Css;
import pl.zgorzalek.web_spy.page.Page;
import pl.zgorzalek.web_spy.value.Value;
import pl.zgorzalek.web_spy.value.ValueService;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RecordService {
    private final RecordRepository recordRepository;
    private final ValueService valueService;

    public void add(Record record) {
        record.setDateAdded(LocalDateTime.now());
        recordRepository.save(record);
    }

    public List<Record> getAllByDownloadId(int id) {
        return recordRepository.findAllByDownloadId(id);
    }

    public int getLastDownloadId() {
        return recordRepository.findLastDownloadId().orElse(0);
    }

    public List<Record> getAllByCss(Css css) {
        return recordRepository.findAllByCss(css);
    }

    public void delete(Record record) {
        List<Value> valueList = valueService.getAllByRecord(record);
        for (Value value : valueList) {
            valueService.delete(value);
        }
        recordRepository.delete(record);
    }

    public List<Record> getRecordSummary(Page page, Css css) {
        int downloadId = getLastDownloadId() + 1;
        try {
            Document document = Jsoup.connect(page.getUrl()).get();
            Element body = document.body();
            List<Element> parentElements = body.getElementsByClass(css.getName());
            Element child = parentElements.get(0);
            List<Element> elementsToCheck = child.getAllElements();
            List<String> cssCollect = new ArrayList<>();
            for (Element element : elementsToCheck) {
                if (!element.className().isEmpty()) {
                    cssCollect.add(element.className());
                }
            }
            for (Element parentElement : parentElements) {
                Record record = new Record();
                record.setCss(css);
                record.setDownloadId(downloadId);
                add(record);
                List<Element> childrenElements = parentElement.getAllElements();
                for (Element childElement : childrenElements) {
                    for (String cssCheck : cssCollect) {
                        if (childElement.className().equals(cssCheck)) {
                            Value value = new Value();
                            value.setRecord(record);
                            value.setValue(childElement.text());
                            valueService.add(value);
                        }
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

    public List<Integer> getDownloadIdByCssId(Long cssId) {
        return recordRepository.findDownloadIdByCss(cssId);
    }

    public Record getFirstByDownloadId(Integer downloadId) {
        return recordRepository.findFirstByDownloadId(downloadId);
    }

    public List<Record> viewRecordSummary(int downloadId) {
        List<Record> records = getAllByDownloadId(downloadId);
        for (Record record : records) {
            record.setValues(valueService.getAllByRecord(record));
        }
        return records;
    }

    public void exportToExcel(HttpServletResponse response, int downloadId) {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Dane");
            int countRow = 0;
            List<Record> records = viewRecordSummary(downloadId);
            for (Record record : records) {
                Row row = sheet.createRow(countRow);
                int countCell = 0;
                for (Value value : record.getValues()) {
                    Cell cell = row.createCell(countCell);
                    cell.setCellValue(value.getValue());
                    countCell++;
                }
                countRow++;
            }
            ServletOutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
