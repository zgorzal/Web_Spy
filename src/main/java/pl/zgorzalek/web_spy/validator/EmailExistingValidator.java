package pl.zgorzalek.web_spy.validator;

import org.springframework.beans.factory.annotation.Autowired;
import pl.zgorzalek.web_spy.user.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailExistingValidator implements ConstraintValidator<EmailExisting, String> {
    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return userService.findByEmail(email) == null;
    }
}