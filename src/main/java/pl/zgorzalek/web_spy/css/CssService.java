package pl.zgorzalek.web_spy.css;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zgorzalek.web_spy.page.Page;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CssService {
    private final CssRepository cssRepository;

    public void add(Css css) {
        cssRepository.save(css);
    }

    public Css findByName(String name) {
        return cssRepository.findFirstByName(name);
    }

    public List<Css> findByPage(Page page) {
        return cssRepository.findAllByPage(page);
    }

    public void delete(Css css) {
        cssRepository.delete(css);
    }
}
