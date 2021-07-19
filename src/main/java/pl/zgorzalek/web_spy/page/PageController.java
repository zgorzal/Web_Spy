package pl.zgorzalek.web_spy.page;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.zgorzalek.web_spy.user.User;
import pl.zgorzalek.web_spy.user.service.UserService;

import javax.validation.Valid;
import java.util.List;


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
    public String view(Model model) {
        List<Page> pages = pageService.getAllByUser(getUser());
        model.addAttribute("pages", pages);
        return "app/selectPage";
    }
}
