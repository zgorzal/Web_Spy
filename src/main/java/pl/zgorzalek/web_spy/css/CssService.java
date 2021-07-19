package pl.zgorzalek.web_spy.css;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CssService {
    private final CssRepository cssRepository;
}
