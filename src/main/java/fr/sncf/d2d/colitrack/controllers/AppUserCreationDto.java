package fr.sncf.d2d.colitrack.controllers;

import fr.sncf.d2d.colitrack.domain.AppUser;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AppUserCreationDto(
        @NotNull(message = "Missing username")
        @NotBlank(message = "Username cannot be empty")
        String username,
        @NotBlank(message = "Password cannot be missing or empty")
        String password
) {

    public AppUser toAppUser() {
        return new AppUser(username, password);
    }
}
