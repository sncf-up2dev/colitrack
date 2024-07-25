package fr.sncf.d2d.colitrack.controllers;

import fr.sncf.d2d.colitrack.domain.AppUser;

public record AppUserCreationDto(
        @Username
        String username,
        @Password
        String password
) {

    public AppUser toAppUser() {
        return new AppUser(username, password);
    }
}
