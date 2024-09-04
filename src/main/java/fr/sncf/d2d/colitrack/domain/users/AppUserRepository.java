package fr.sncf.d2d.colitrack.domain.users;

import org.springframework.data.repository.ListCrudRepository;

public interface AppUserRepository extends ListCrudRepository<AppUser, String> {
}
