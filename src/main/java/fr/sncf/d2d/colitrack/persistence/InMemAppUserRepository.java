package fr.sncf.d2d.colitrack.persistence;

import fr.sncf.d2d.colitrack.domain.AppUser;
import fr.sncf.d2d.colitrack.domain.AppUserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemAppUserRepository implements AppUserRepository {

    private final List<AppUser> data = new ArrayList<>();

    @Override
    public AppUser save(AppUser input) {
        this.data.add(input);
        return input;
    }

    @Override
    public Optional<AppUser> findById(String id) {
        return this.data.stream()
                .filter(appUser -> id.equals(appUser.getUsername()))
                .findAny();
    }

    @Override
    public List<AppUser> find() {
        return this.data;
    }

    @Override
    public void delete(String id) {
        this.data.removeIf(appUser -> id.equals(appUser.getUsername()));
    }
}
