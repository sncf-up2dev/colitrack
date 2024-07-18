package fr.sncf.d2d.colitrack.domain;

import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface AppUserRepository extends Repository<AppUser, String> {

    AppUser save(AppUser input);
    Optional<AppUser> findById(String id);
    List<AppUser> findAll();
    void deleteById(String id);
}
