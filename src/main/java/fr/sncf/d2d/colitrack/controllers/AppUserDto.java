package fr.sncf.d2d.colitrack.controllers;

public record AppUserDto(
        String username
) {
    static AppUserDto from(AppUser source) {
        return new AppUserDto(source.getUsername());
    }
}
