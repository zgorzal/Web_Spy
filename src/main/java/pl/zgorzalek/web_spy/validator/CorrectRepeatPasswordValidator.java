package pl.zgorzalek.web_spy.validator;

import pl.zgorzalek.web_spy.user.DTO.UserRegisterDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CorrectRepeatPasswordValidator implements ConstraintValidator<CorrectRepeatPassword, UserRegisterDTO> {

    @Override
    public boolean isValid(UserRegisterDTO userRegisterDTO, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = userRegisterDTO.getPassword().equals(userRegisterDTO.getRepeatPassword());
        if(!isValid){
            constraintValidatorContext.buildConstraintViolationWithTemplate("Podane hasła muszą być identyczne").addPropertyNode("repeatPassword").addConstraintViolation();
        }
        return isValid;

    }
}
