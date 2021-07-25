package pl.zgorzalek.web_spy.page;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.zgorzalek.web_spy.css.Css;
import pl.zgorzalek.web_spy.css.CssService;
import pl.zgorzalek.web_spy.record.Record;
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
    private final CssService cssService;

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
        model.addAttribute("title", page.getName());
        model.addAttribute("records", recordService.getRecordSummary(id, cssClass));
        return "app/dataSummary";
    }

    @GetMapping("/list")
    public String viewPageList(Model model) {
        List<Page> pages = pageService.getAllByUser(getUser());
        model.addAttribute("pages", pages);
        return "app/pageList";
    }

    @GetMapping("/reports")
    public String viewReports(Model model) {
        List<Page> pages = pageService.getAllByUser(getUser());
        for (Page page : pages) {
            page.setCss(cssService.findByPage(page));
        }
        model.addAttribute("pages", pages);
        return "app/reportsList";
    }

    @GetMapping("/reports/{cssClass}")
    public String viewReportsSummaryList(@PathVariable String cssClass, Model model) {
        model.addAttribute("cssClass", cssClass);
        Css css = cssService.findByName(cssClass);
        List<Integer> downloadIdList = recordService.getDownloadIdByCssId(css.getId());
        List<Record> records = new ArrayList<>();
        for (Integer downloadId : downloadIdList) {
            records.add(recordService.getFirstByDownloadId(downloadId));
        }
        model.addAttribute("records", records);
        return "app/dataSummaryList";
    }

    @GetMapping("/report/{downloadId}")
    public String viewReportSummary(@PathVariable int downloadId, Model model) {
        model.addAttribute("title", "Raport");
        model.addAttribute("records", recordService.viewRecordSummary(downloadId));
        return "app/dataSummary";
    }
}