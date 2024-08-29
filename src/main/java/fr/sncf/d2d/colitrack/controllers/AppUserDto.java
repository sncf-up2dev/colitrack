package fr.sncf.d2d.colitrack.controllers;

import fr.sncf.d2d.colitrack.domain.AppUser;
import fr.sncf.d2d.colitrack.domain.AppUserRole;

public record AppUserDto(
        String username,
        AppUserRole role
) {
    static AppUserDto from(AppUser source) {
        return new AppUserDto(source.getUsername(), source.getRole());
    }
}
