package pl.zgorzalek.web_spy.page;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.zgorzalek.web_spy.css.Css;
import pl.zgorzalek.web_spy.css.CssService;
import pl.zgorzalek.web_spy.record.Record;
import pl.zgorzalek.web_spy.record.RecordService;
import pl.zgorzalek.web_spy.user.CurrentUser;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/page")
public class PageController {
    private final PageService pageService;
    private final RecordService recordService;
    private final CssService cssService;

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("page", new Page());
        model.addAttribute("title", "Dodaj stronę");
        return "app/addPage";
    }

    @PostMapping("/add")
    public String add(@Valid Page page, BindingResult result, @AuthenticationPrincipal CurrentUser currentUser) {
        if (result.hasErrors()) {
            return "app/addPage";
        }
        page.setDateAdded(LocalDateTime.now());
        page.setUser(currentUser.getUser());
        pageService.add(page);
        return "redirect:/";
    }

    @GetMapping("")
    public String viewAll(Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        List<Page> pages = pageService.getAllByUser(currentUser.getUser());
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
        pageService.add(page);
        Css css = cssService.findByName(cssClass);
        if (css == null) {
            css = new Css();
        }
        css.setName(cssClass);
        css.setPage(page);
        cssService.add(css);
        model.addAttribute("title", page.getName());
        model.addAttribute("records", recordService.getRecordSummary(page, css));
        return "app/dataSummary";
    }

    @GetMapping("/list")
    public String viewPageList(Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        List<Page> pages = pageService.getAllByUser(currentUser.getUser());
        model.addAttribute("pages", pages);
        return "app/pageList";
    }

    @GetMapping("/reports")
    public String viewReports(Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        List<Page> pages = pageService.getAllByUser(currentUser.getUser());
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

    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable Long id, Model model) {
        Page page = pageService.findById(id);
        model.addAttribute("page", page);
        model.addAttribute("title", "Edytuj stronę");
        return "app/addPage";
    }

    @PostMapping("/edit/{id}")
    public String editPage(@Valid Page page, BindingResult result) {
        if (result.hasErrors()) {
            return "app/addPage";
        }
        pageService.add(page);
        return "redirect:/page/list";
    }

    @GetMapping("/delete/{id}")
    public String deletePage(@PathVariable Long id, Model model) {
        Page page = pageService.findById(id);
        model.addAttribute("page", page);
        return "app/confirmDelete";
    }

    @PostMapping("/delete/{id}")
    public String deletePage(@PathVariable Long id) {
        Page page = pageService.findById(id);
        pageService.deletePage(page);
        return "redirect:/page/list";
    }

    @GetMapping("/report/excel/{downloadId}")
    public void exportToExcel(@PathVariable int downloadId, HttpServletResponse response) {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachement; filename=Raport.xlsx";
        response.setHeader(headerKey, headerValue);
        recordService.exportToExcel(response, downloadId);
    }

}