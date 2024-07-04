package fr.sncf.d2d.colitrack.controllers;

import fr.sncf.d2d.colitrack.domain.AppUser;

public record AppUserDto(
        String username
) {
    static AppUserDto from(AppUser source) {
        return new AppUserDto(source.getUsername());
    }
}
