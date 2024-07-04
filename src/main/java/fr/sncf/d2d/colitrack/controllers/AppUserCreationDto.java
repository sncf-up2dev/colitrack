package fr.sncf.d2d.colitrack.controllers;

import fr.sncf.d2d.colitrack.domain.AppUser;

public record AppUserCreationDto(
        String username,
        String password
) {

    public AppUser toAppUser() {
        return new AppUser(username, password);
    }
}
