package pl.zgorzalek.web_spy.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.zgorzalek.web_spy.user.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/settings")
    public String settings() { // dodanie usera zalogowanego pod kluczem user
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
    public String changePassword(@Valid User user, BindingResult result, HttpServletRequest request) { // dwa dto
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

    @GetMapping("/list")
    public String userList(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "app/admin/userList";
    }
}
