package pl.zgorzalek.web_spy.css;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
}
