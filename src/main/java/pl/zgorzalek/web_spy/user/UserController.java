package pl.zgorzalek.web_spy.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    private final BCryptPasswordEncoder passwordEncoder;

    @ModelAttribute("user")
    public User getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        return userService.findByEmail(email);
    }

    @GetMapping("/settings")
    public String settings() {
        return "app/settings";
    }

    @PostMapping("/settings")
    public String settings(@Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "app/settings";
        }
        userService.update(user);
        return "redirect:/user/settings";
    }

    @PostMapping("/settings/password")
    public String changePassword(@Valid User user, BindingResult result, HttpServletRequest request) {
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String repeatNewPassword = request.getParameter("repeatNewPassword");
        if (passwordEncoder.matches(oldPassword, user.getPassword())
                && newPassword.equals(repeatNewPassword)) {
            userService.updatePassword(user, newPassword);
        }
        return "redirect:/user/settings";
    }

    @RequestMapping("/account")
    public String account() {
        return "app/account";
    }
}
