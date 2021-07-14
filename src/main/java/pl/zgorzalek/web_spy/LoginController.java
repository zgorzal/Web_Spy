package pl.zgorzalek.web_spy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.zgorzalek.web_spy.user.User;
import pl.zgorzalek.web_spy.user.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid User user, BindingResult result, HttpServletRequest request) {
        String email = request.getParameter("email");
        User checkUser = userService.findByEmail(email);
        String repeatPassword = request.getParameter("repeatPassword");
        if (checkUser != null) {
            request.setAttribute("existsEmail", "Użytkownik o podanym adresie email już istnieje");
            return "/register";
        } else if (result.hasErrors()) {
            return "/register";
        } else if (!repeatPassword.equals(user.getPassword())) {
            request.setAttribute("errorPassword", "Podane hasła nie są identyczne");
            return "/register";
        }
        userService.add(user);
        return "redirect:/login";
    }
}
