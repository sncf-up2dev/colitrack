package fr.sncf.d2d.colitrack.controllers;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@NotBlank(message = "Password cannot be missing or empty")
@Size(min = 8, message = "Password should have at least 8 characters")
@Constraint(validatedBy = {})
public @interface Password {

    String message() default "Missing or invalid password";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
