package pl.zgorzalek.web_spy.validator;

import pl.zgorzalek.web_spy.user.DTO.UserRegisterDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CorrectRepeatPasswordValidator implements ConstraintValidator<CorrectRepeatPassword, UserRegisterDTO> {

    @Override
    public boolean isValid(UserRegisterDTO userRegisterDTO, ConstraintValidatorContext constraintValidatorContext) {
        return userRegisterDTO.getPassword().equals(userRegisterDTO.getRepeatPassword());
    }
}
