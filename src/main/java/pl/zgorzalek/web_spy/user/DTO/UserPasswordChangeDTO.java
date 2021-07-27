package pl.zgorzalek.web_spy.user.DTO;

import lombok.Data;
import pl.zgorzalek.web_spy.validator.CorrectRepeatPassChange;
import pl.zgorzalek.web_spy.validator.IncorrectPassword;

import javax.validation.constraints.NotBlank;

@Data
@IncorrectPassword
@CorrectRepeatPassChange
public class UserPasswordChangeDTO {

    private Long id;

    @NotBlank
    private String oldPassword;

    @NotBlank
    private String newPassword;

    @NotBlank
    private String repeatPassword;
}
