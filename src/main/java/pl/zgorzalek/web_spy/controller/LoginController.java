package pl.zgorzalek.web_spy.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.zgorzalek.web_spy.user.DTO.UserRegisterDTO;
import pl.zgorzalek.web_spy.user.service.UserService;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@Log4j2
@Controller
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;

    @GetMapping("/login")
    public String login(HttpServletRequest request) {
        Map<String, String[]> param = request.getParameterMap();
        if (param.containsKey("error")) {
            request.setAttribute("errorInput", "Błędny email lub hasło");
            log.info("error login");
        }
        log.info("login");
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userRegisterDTO", new UserRegisterDTO());
        log.info("register");
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid UserRegisterDTO userRegisterDTO,
                           BindingResult result,
                           HttpServletRequest request)
            throws UnsupportedEncodingException, MessagingException {
        if (result.hasErrors()) {
            log.info(result.toString());
            return "/register";
        }
        userService.add(userRegisterDTO, getSiteURL(request));
        log.info("Register user: " + userRegisterDTO.getFirstName());
        return "registerConfirm";
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }

    @GetMapping("/verify")
    public String verifyUser(@Param("code") String code) {
        if (userService.verify(code)) {
            return "verifySuccess";
        } else {
            return "verifyFail";
        }
    }
}
