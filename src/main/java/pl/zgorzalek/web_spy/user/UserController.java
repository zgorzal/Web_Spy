package pl.zgorzalek.web_spy.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.zgorzalek.web_spy.app.SecurityConfig;
import pl.zgorzalek.web_spy.user.DTO.UserDataChangeDTO;
import pl.zgorzalek.web_spy.user.service.SpringDataUserDetailsService;
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
    public String settings(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        UserDataChangeDTO userDataChangeDTO =
                new UserDataChangeDTO(currentUser.getUser().getId(),
                        currentUser.getUser().getFirstName(),
                        currentUser.getUser().getLastName(),
                        currentUser.getUser().getEmail());
        model.addAttribute("userDataChangeDTO", userDataChangeDTO);
        return "app/settings";
    }

    @PostMapping("/settings")
    public String settings(@Valid UserDataChangeDTO userDataChangeDTO,
                           BindingResult result,
                           @AuthenticationPrincipal CurrentUser currentUser) {
        if (result.hasErrors()) {
            return "app/settings";
        }
        userService.update(userDataChangeDTO);
        currentUser.getUser().setFirstName(userDataChangeDTO.getFirstName());
        currentUser.getUser().setLastName(userDataChangeDTO.getLastName());
        currentUser.getUser().setEmail(userDataChangeDTO.getEmail());
        Authentication authentication = new UsernamePasswordAuthenticationToken(currentUser, currentUser.getPassword(), currentUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
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

    @GetMapping("/list")
    public String userList(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "app/admin/userList";
    }
}
