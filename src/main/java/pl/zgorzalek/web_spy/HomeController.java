package pl.zgorzalek.web_spy;

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
    private final UserService userService;

    @ModelAttribute("user")
    public User getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        return userService.findByEmail(email);
    }

    @RequestMapping("/")
    public String loginOn() {
        return "app/dashboard";
    }
}
