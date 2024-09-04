package fr.sncf.d2d.colitrack.controllers.users;

import fr.sncf.d2d.colitrack.domain.users.AppUser;
import fr.sncf.d2d.colitrack.domain.users.AppUserRole;

public record AppUserDto(
        String username,
        AppUserRole role
) {
    static AppUserDto from(AppUser source) {
        return new AppUserDto(source.getUsername(), source.getRole());
    }
}
