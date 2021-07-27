package pl.zgorzalek.web_spy.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.zgorzalek.web_spy.user.DTO.UserDataChangeDTO;
import pl.zgorzalek.web_spy.user.DTO.UserPasswordChangeDTO;
import pl.zgorzalek.web_spy.user.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

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

    @GetMapping("/settings/password")
    public String settingsPass(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        UserPasswordChangeDTO userPasswordChangeDTO = new UserPasswordChangeDTO();
        userPasswordChangeDTO.setId(currentUser.getUser().getId());
        model.addAttribute("userPasswordChangeDTO", userPasswordChangeDTO);
        return "app/settingsPass";
    }

    @PostMapping("/settings/password")
    public String changePassword(@Valid UserPasswordChangeDTO userPasswordChangeDTO,
                                 BindingResult result) {
        if (result.hasErrors()) {
            return "app/settingsPass";
        }
        userService.updatePassword(userPasswordChangeDTO);
        return "redirect:/user/settings/password";
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
