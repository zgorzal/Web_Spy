package pl.zgorzalek.web_spy.page;

import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.zgorzalek.web_spy.user.User;
import pl.zgorzalek.web_spy.user.service.UserService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/page")
public class PageController {
    private final PageService pageService;
    private final UserService userService;

    @ModelAttribute("user")
    public User getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        return userService.findByEmail(email);
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("page", new Page());
        return "app/addPage";
    }

    @PostMapping("/add")
    public String add(@Valid Page page, BindingResult result) {
        if (result.hasErrors()) {
            return "app/addPage";
        }
        pageService.add(page, getUser());
        return "redirect:/";
    }

    @GetMapping("")
    public String viewAll(Model model) {
        List<Page> pages = pageService.getAllByUser(getUser());
        model.addAttribute("pages", pages);
        return "app/selectPage";
    }

    @GetMapping("/{id}")
    public String viewPage(@PathVariable Long id, Model model) {
        Page page = pageService.findById(id);
        try {
            Document document = Jsoup.connect(page.getUrl()).get();
            Element body = document.body();
            List<Element> elements = body.getAllElements();
            Map<String, Integer> pageMaps = new HashMap<>();
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < elements.size(); i++) {
                if (!elements.get(i).className().isEmpty()) {
                    if (pageMaps.containsKey(elements.get(i).className())) {
                        int count = pageMaps.get(elements.get(i).className()) + 1;
                        pageMaps.put(elements.get(i).className(), count);
                    } else {
                        pageMaps.put(elements.get(i).className(), 1);
                    }
                }
            }

            pageMaps.entrySet().stream()
                    .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                    .filter(a -> a.getValue() > 1)
                    .forEach(a -> sb.append("<a href=\"#\" class=\" cssWebSpy btn btn-light btn-icon-split\">\n" +
                            "                                        <span class=\"text\">" + a.getKey() + "</span>\n" +
                            "                                    </a>"));

            body.before("<link href=\"../../../theme/vendor/fontawesome-free/css/all.min.css\" rel=\"stylesheet\" type=\"text/css\">\n" +
                    "<link href=\"../../../theme/css/sb-admin-2.min.css\" rel=\"stylesheet\">\n" +
                    "<div class=\"card shadow mb-4\">\n" +
                    "    <a href=\"#collapseCardExample\" class=\"d-block card-header py-3 collapsed\" data-toggle=\"collapse\" role=\"button\" aria-expanded=\"false\" aria-controls=\"collapseCardExample\">\n" +
                    "        <h6 class=\"m-0 font-weight-bold text-primary\">Wybierz elementy do pobrania</h6>\n" +
                    "    </a>\n" +
                    "    <div class=\"collapse\" id=\"collapseCardExample\" style=\"\">\n" +
                    "        <div class=\"card-body\">\n" +
                    "           <div class=\"row\">\n" +
                    "               " + sb.toString() + "\n" +
                    "           </div>\n" +
                    "<a href=\"../page\" class=\"btn btn-warning btn-icon-split\">\n" +
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

            model.addAttribute("page", document);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "app/page";
    }
}
