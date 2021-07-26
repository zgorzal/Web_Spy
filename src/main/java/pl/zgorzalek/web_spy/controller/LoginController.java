package pl.zgorzalek.web_spy.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.zgorzalek.web_spy.user.DTO.UserRegisterDTO;
import pl.zgorzalek.web_spy.user.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;

    @GetMapping("/login")
    public String login(HttpServletRequest request) {
        Map<String, String[]> param = request.getParameterMap();
        if (param.containsKey("error")) {
            request.setAttribute("errorInput", "Błędny email lub hasło");
        }
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userRegisterDTO", new UserRegisterDTO());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegisterDTO userRegisterDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "/register";
        }
        userService.add(userRegisterDTO);
        return "redirect:/login";
    }
}
