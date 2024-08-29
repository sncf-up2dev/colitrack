package fr.sncf.d2d.colitrack.controllers;

import fr.sncf.d2d.colitrack.domain.AppUser;
import fr.sncf.d2d.colitrack.domain.AppUserRole;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;

public record AppUserCreationDto(
        @Username
        String username,
        @Password
        String password,
        @NotNull
        AppUserRole role
) {

    @AssertTrue(message = "Password should not contain username")
    public boolean isUsernameInPassword() {
        return !this.password.contains(this.username);
    }

    public AppUser toAppUser() {
        return new AppUser(username, password, role);
    }
}
