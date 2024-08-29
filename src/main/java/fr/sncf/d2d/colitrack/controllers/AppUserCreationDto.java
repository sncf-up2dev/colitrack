package fr.sncf.d2d.colitrack.controllers;

import fr.sncf.d2d.colitrack.domain.AppUser;
import fr.sncf.d2d.colitrack.domain.AppUserRole;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public record AppUserCreationDto(
        @Username
        String username,
        @Password
        String password,
        @NotNull
        String role
) {

    @AssertTrue(message = "Password should not contain username")
    public boolean isUsernameInPassword() {
        return !this.password.contains(this.username);
    }

    public AppUser toAppUser() {
        try {
            return new AppUser(username, password, AppUserRole.valueOf(role));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Illegal value %s for role".formatted(role),
                    e
            );
        }
    }
}
