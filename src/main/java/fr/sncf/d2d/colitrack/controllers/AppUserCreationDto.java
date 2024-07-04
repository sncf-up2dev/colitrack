package fr.sncf.d2d.colitrack.controllers;

public record AppUserCreationDto(
        String username,
        String password
) {

    public AppUser toAppUser() {
        return new AppUser(username, password);
    }
}
