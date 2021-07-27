package pl.zgorzalek.web_spy.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = EmailExistingChangeValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailExistingChange {
    String message() default "{EmailExisting.error.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
