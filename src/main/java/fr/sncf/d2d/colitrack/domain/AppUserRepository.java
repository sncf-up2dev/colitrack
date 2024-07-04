package fr.sncf.d2d.colitrack.domain;

import java.util.List;
import java.util.Optional;

public interface AppUserRepository {

    AppUser save(AppUser input);
    Optional<AppUser> findById(String id);
    List<AppUser> find();
    void delete(String id);
}
