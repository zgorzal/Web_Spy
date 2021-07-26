package pl.zgorzalek.web_spy.user.DTO;

import lombok.Data;
import pl.zgorzalek.web_spy.validator.CorrectRepeatPassword;
import pl.zgorzalek.web_spy.validator.EmailExisting;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@CorrectRepeatPassword
public class UserRegisterDTO {

    @NotBlank
    @Size(min = 2, max = 30)
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 30)
    private String lastName;

    @NotBlank
    @Email
    @EmailExisting
    @Size(max = 50)
    @Column(unique = true)
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String repeatPassword;

}
