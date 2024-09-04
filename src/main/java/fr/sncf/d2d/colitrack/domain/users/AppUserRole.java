package fr.sncf.d2d.colitrack.domain.users;

import java.util.List;

public enum AppUserRole {
    ADMIN("MANAGE", "WRITE", "READ"),
    POSTMAN("WRITE", "READ"),
    VISITOR("READ");

    private final List<String> authorities;

    AppUserRole(String... authorities) {
        this.authorities = List.of(authorities);
    }

    public List<String> getAuthorities() {
        return authorities;
    }
}
