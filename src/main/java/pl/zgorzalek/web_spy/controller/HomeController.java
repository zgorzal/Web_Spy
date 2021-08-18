package pl.zgorzalek.web_spy.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequiredArgsConstructor
public class HomeController {

    @RequestMapping("/")
    public String loginOn() {
        log.info("Success login");
        return "app/dashboard";
    }
}
