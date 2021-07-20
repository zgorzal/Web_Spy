package pl.zgorzalek.web_spy.page;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zgorzalek.web_spy.user.User;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PageService {
    private final PageRepository pageRepository;

    public void add(Page page, User user) {
        page.setDateAdded(LocalDateTime.now());
        page.setDateUpdate(LocalDateTime.now());
        page.setUser(user);
        pageRepository.save(page);
    }

    public List<Page> getAllByUser(User user) {
        return pageRepository.findPagesByUser(user);
    }

    public Page findById(Long id) {
        return pageRepository.findById(id).orElse(null);
    }
}
