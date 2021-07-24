package pl.zgorzalek.web_spy.page;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.zgorzalek.web_spy.css.CssService;
import pl.zgorzalek.web_spy.record.RecordService;
import pl.zgorzalek.web_spy.user.User;
import pl.zgorzalek.web_spy.user.service.UserService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/page")
public class PageController {
    private final PageService pageService;
    private final UserService userService;
    private final RecordService recordService;

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
        page.setDateAdded(LocalDateTime.now());
        page.setUser(getUser());
        pageService.add(page);
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
        model.addAttribute("page", pageService.createPageToView(id));
        return "app/page";
    }

    @GetMapping("/{id}/{cssClass}")
    public String viewDataSummary(@PathVariable Long id, @PathVariable String cssClass, Model model) {
        Page page = pageService.findById(id);
        model.addAttribute("page", page);
        model.addAttribute("records", recordService.getRecordSummary(id, cssClass));
        return "app/dataSummary";
    }

    @GetMapping("/list")
    public String viewPageList(Model model) {
        List<Page> pages = pageService.getAllByUser(getUser());
        model.addAttribute("pages", pages);
        return "app/pageList";
    }
}