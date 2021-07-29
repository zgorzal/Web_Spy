package pl.zgorzalek.web_spy.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.zgorzalek.web_spy.user.DTO.UserPasswordChangeDTO;
import pl.zgorzalek.web_spy.user.User;
import pl.zgorzalek.web_spy.user.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IncorrectPasswordValidator implements ConstraintValidator<IncorrectPassword, UserPasswordChangeDTO> {
    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public boolean isValid(UserPasswordChangeDTO userPasswordChangeDTO, ConstraintValidatorContext constraintValidatorContext) {
        User user = userService.findById(userPasswordChangeDTO.getId());
        boolean isValid = passwordEncoder.matches(userPasswordChangeDTO.getOldPassword(), user.getPassword());
        if (!isValid) {
            constraintValidatorContext.buildConstraintViolationWithTemplate("Podano błędne hasło").addPropertyNode("oldPassword").addConstraintViolation();
        }
        return isValid;
    }
}
