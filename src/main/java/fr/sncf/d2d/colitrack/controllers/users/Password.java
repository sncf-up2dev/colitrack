package fr.sncf.d2d.colitrack.controllers.users;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@NotNull(message = "Password cannot be missing")
@Size(min = 8, message = "Password should have at least 8 characters")
@Constraint(validatedBy = PasswordConstraintValidator.class)
public @interface Password {

    String message() default "Password should not contain company's name";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
