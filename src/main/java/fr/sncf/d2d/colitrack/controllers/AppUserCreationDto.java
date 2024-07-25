package fr.sncf.d2d.colitrack.controllers;

import fr.sncf.d2d.colitrack.domain.AppUser;
import jakarta.validation.constraints.AssertTrue;

public record AppUserCreationDto(
        @Username
        String username,
        @Password
        String password
) {

    @AssertTrue(message = "Password should not contain username")
    public boolean isUsernameInPassword() {
        return !this.password.contains(this.username);
    }

    public AppUser toAppUser() {
        return new AppUser(username, password);
    }
}
