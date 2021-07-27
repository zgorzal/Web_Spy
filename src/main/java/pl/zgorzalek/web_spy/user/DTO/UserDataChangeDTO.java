package pl.zgorzalek.web_spy.user.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.zgorzalek.web_spy.validator.EmailExistingChange;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@EmailExistingChange
public class UserDataChangeDTO {

    private Long id;

    @NotBlank
    @Size(min = 2, max = 30)
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 30)
    private String lastName;

    @NotBlank
    @Email
    @Size(max = 50)
    @Column(unique = true)
    private String email;
}
