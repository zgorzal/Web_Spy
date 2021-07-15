package pl.zgorzalek.web_spy.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.zgorzalek.web_spy.user.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/settings")
    public String settings(Model model, HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User user = userService.findByEmail(email);
        request.setAttribute("firstName", user.getFirstName());
        request.setAttribute("lastName", user.getLastName());
        model.addAttribute("user", user);
        return "settings";
    }

    @PostMapping("/settings")
    public String settings(@Valid User user, BindingResult result) {
        if (result.hasFieldErrors("firstName")
                || result.hasFieldErrors("lastName")) {
            return "settings";
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User userAuth = userService.findByEmail(email);
        userAuth.setFirstName(user.getFirstName());
        userAuth.setLastName(user.getLastName());
        userService.update(userAuth);
        return "redirect:/user/settings";
    }
}
