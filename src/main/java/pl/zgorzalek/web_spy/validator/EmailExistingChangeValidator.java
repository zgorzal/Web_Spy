package pl.zgorzalek.web_spy.validator;

import org.springframework.beans.factory.annotation.Autowired;
import pl.zgorzalek.web_spy.user.DTO.UserDataChangeDTO;
import pl.zgorzalek.web_spy.user.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailExistingChangeValidator implements ConstraintValidator<EmailExistingChange, UserDataChangeDTO> {
    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(UserDataChangeDTO userDataChangeDTO, ConstraintValidatorContext constraintValidatorContext) {
        return userService.findByEmail(userDataChangeDTO.getEmail()) == null ||
                userService.findById(userDataChangeDTO.getId()).getEmail().equals(userDataChangeDTO.getEmail());
    }
}
