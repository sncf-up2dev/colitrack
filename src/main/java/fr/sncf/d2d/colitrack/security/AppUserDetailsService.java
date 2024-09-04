package fr.sncf.d2d.colitrack.security;

import fr.sncf.d2d.colitrack.domain.users.AppUser;
import fr.sncf.d2d.colitrack.domain.users.AppUserRepository;
import fr.sncf.d2d.colitrack.domain.users.AppUserRole;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AppUserDetailsService implements UserDetailsService {

    private final AppUserRepository appUserRepository;
    private final AppUser superuser;

    public AppUserDetailsService(
            AppUserRepository appUserRepository,
            PasswordEncoder passwordEncoder,
            @Value("${colitrack.security.superuser.username:}")
            String superuserUsername,
            @Value("${colitrack.security.superuser.password:}")
            String superuserPassword) {
        this.appUserRepository = appUserRepository;
        if (!superuserUsername.isBlank() && !superuserPassword.isBlank()) {
            String encodedPassword = passwordEncoder.encode(superuserPassword);
            this.superuser = new AppUser(superuserUsername, encodedPassword, AppUserRole.ADMIN);
        } else {
            this.superuser = null;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (this.superuser != null && username.equals(superuser.getUsername())) {
            return this.mapAppUser(this.superuser);
        }
        return this.appUserRepository.findById(username)
                .map(this::mapAppUser)
                .orElseThrow(() -> new UsernameNotFoundException("Username %s not found".formatted(username)));
    }

    private UserDetails mapAppUser(AppUser appUser) {
        List<SimpleGrantedAuthority> authorities = appUser.getRole().getAuthorities().stream()
                .map(SimpleGrantedAuthority::new)
                .toList();
        return new User(appUser.getUsername(), appUser.getPassword(), authorities);
    }
}
