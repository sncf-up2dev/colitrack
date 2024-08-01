package fr.sncf.d2d.colitrack.domain;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserService {

    private final AppUserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public AppUserService(
            AppUserRepository repository,
            PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<AppUser> retrieveAll() {
        return this.repository.findAll();
    }

    public AppUser retrieve(String username) {
        return this.repository.findById(username)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    public AppUser create(AppUser input) {
        Optional<?> maybeUser = this.repository.findById(input.getUsername());
        if (maybeUser.isPresent()) {
            throw new DuplicateException("User with same username already exists");
        }
        input.setPassword(this.passwordEncoder.encode(input.getPassword()));
        return this.repository.save(input);
    }

    public void delete(String username) {
        if (!this.repository.existsById(username)) {
            throw new NotFoundException("Username not found");
        }
        this.repository.deleteById(username);
    }
}
