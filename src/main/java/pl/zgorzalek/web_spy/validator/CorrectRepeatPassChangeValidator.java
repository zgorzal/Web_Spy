package pl.zgorzalek.web_spy.validator;

import pl.zgorzalek.web_spy.user.DTO.UserPasswordChangeDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CorrectRepeatPassChangeValidator implements ConstraintValidator<CorrectRepeatPassChange, UserPasswordChangeDTO> {

    @Override
    public boolean isValid(UserPasswordChangeDTO userPasswordChangeDTO, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = userPasswordChangeDTO.getNewPassword().equals(userPasswordChangeDTO.getRepeatPassword());
        if(!isValid){
            constraintValidatorContext.buildConstraintViolationWithTemplate("Podane hasła muszą być identyczne").addPropertyNode("newPassword").addConstraintViolation();
        }
        return isValid;
    }
}
