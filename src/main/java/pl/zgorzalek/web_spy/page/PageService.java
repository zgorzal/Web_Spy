package pl.zgorzalek.web_spy.page;

import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;
import pl.zgorzalek.web_spy.css.Css;
import pl.zgorzalek.web_spy.css.CssService;
import pl.zgorzalek.web_spy.user.User;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class PageService {
    private final PageRepository pageRepository;
    private final CssService cssService;

    public void add(Page page) {
        page.setDateUpdate(LocalDateTime.now());
        pageRepository.save(page);
    }

    public List<Page> getAllByUser(User user) {
        return pageRepository.findPagesByUser(user);
    }

    public Page findById(Long id) {
        return pageRepository.findById(id).orElse(null);
    }

    public void deletePage(Page page) {
        List<Css> cssList = cssService.findByPage(page);
        for (Css css : cssList) {
            cssService.delete(css);
        }
        pageRepository.delete(page);
    }

    public Document createPageToView(Long pageId) {
        Page page = findById(pageId);
        Document document = new Document(page.getUrl());
        try {
            document = Jsoup.connect(page.getUrl()).get();
            Element body = document.body();
            List<Element> bodyElements = body.getAllElements();
            Map<String, Integer> pageMaps = new HashMap<>();
            StringBuilder sb = new StringBuilder();
            for (Element element : bodyElements) {
                if (!element.className().isEmpty()) {
                    if (pageMaps.containsKey(element.className())) {
                        int count = pageMaps.get(element.className()) + 1;
                        pageMaps.put(element.className(), count);
                    } else {
                        pageMaps.put(element.className(), 1);
                    }
                }
            }
            pageMaps.entrySet().stream()
                    .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                    .filter(a -> a.getValue() > 1)
                    .forEach(a -> sb.append("<a href=\"#confirmWebSpy\" class=\" cssWebSpy\">\n" + a.getKey() +"</a><br>"));

            body.before("<link href=\"../../../theme/vendor/fontawesome-free/css/all.min.css\" rel=\"stylesheet\" type=\"text/css\">\n" +
                    "<link href=\"../../../theme/css/sb-admin-2.min.css\" rel=\"stylesheet\">\n" +
                    "<div class=\"card shadow mb-4\">\n" +
                    "    <a href=\"#collapseCardExample\" class=\"d-block card-header py-3 collapsed\" data-toggle=\"collapse\" role=\"button\" aria-expanded=\"false\" aria-controls=\"collapseCardExample\">\n" +
                    "        <h6 class=\"m-0 font-weight-bold text-primary\">Wybierz elementy do pobrania</h6>\n" +
                    "    </a>\n" +
                    "    <div class=\"collapse\" id=\"collapseCardExample\" style=\"\">\n" +
                    "        <div class=\"card-body\">\n" +
                    "           <div>\n" +
                    "               " + sb.toString() + "\n" +
                    "           </div>\n" +
                    "<a href=\"/page\" class=\"btn btn-warning btn-icon-split\">\n" +
                    "         <span class=\"icon text-white-50\">\n" +
                    "                   <i class=\"fas fa-exclamation-triangle\"></i>\n" +
                    "        </span>\n" +
                    "        <span class=\"text\">Powrót</span>\n" +
                    " </a>\n" +
                    "<a href=\"#\" id=\"confirmWebSpy\" class=\"btn btn-success btn-icon-split\">\n" +
                    "         <span class=\"icon text-white-50\">\n" +
                    "                   <i class=\"fas fa-check\"></i>\n" +
                    "         </span>\n" +
                    " <span class=\"text\">Pobierz dane</span>\n" +
                    " </a>" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "</div>\n" +
                    "<script src=\"../../../theme/vendor/jquery/jquery.min.js\"></script>\n" +
                    "<script src=\"../../../theme/vendor/bootstrap/js/bootstrap.bundle.min.js\"></script>\n" +
                    "<script src=\"../../../theme/js/web-spy.js\"></script>");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }

}
