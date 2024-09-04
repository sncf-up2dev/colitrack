package fr.sncf.d2d.colitrack.controllers.users;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@NotNull(message = "Missing username")
@NotBlank(message = "Username cannot be empty")
@Pattern(regexp = "[a-zA-Z][a-zA-Z0-9_]{2,}", message = "Username must start with alpha and be at least 3-character long")
@Constraint(validatedBy = {})
public @interface Username {

    String message() default "Missing or invalid username";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
