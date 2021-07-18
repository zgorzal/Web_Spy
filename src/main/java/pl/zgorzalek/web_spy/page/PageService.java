package pl.zgorzalek.web_spy.page;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class PageService {
    private final PageRepository pageRepository;

    public void add(Page page) {
        page.setDateAdded(LocalDateTime.now());
        page.setDateUpdate(LocalDateTime.now());
        pageRepository.save(page);
    }
}
