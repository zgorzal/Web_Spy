package pl.zgorzalek.web_spy.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.zgorzalek.web_spy.user.User;
import pl.zgorzalek.web_spy.user.service.UserService;

@Controller
@RequiredArgsConstructor
public class HomeController {

    @RequestMapping("/")
    public String loginOn() {
        return "app/dashboard";
    }
}
