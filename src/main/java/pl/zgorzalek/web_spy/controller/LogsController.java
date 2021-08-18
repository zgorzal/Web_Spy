package pl.zgorzalek.web_spy.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Log4j2
@Controller
@RequiredArgsConstructor
public class LogsController {

    @GetMapping("/logs")
    public String viewLogs(Model model) {
        File file = new File("app.log");
        List<String> logs = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                logs.add(scanner.nextLine() + "\n");
            }
            model.addAttribute("logs", logs);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return "app/admin/logs";
    }
}
