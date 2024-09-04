package fr.sncf.d2d.colitrack.domain;

import fr.sncf.d2d.colitrack.domain.users.AppUser;
import fr.sncf.d2d.colitrack.domain.users.AppUserRepository;
import fr.sncf.d2d.colitrack.domain.users.AppUserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AppUserServiceTest {

    @Mock
    AppUserRepository repository;

    @Spy
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @InjectMocks
    AppUserService sut;

    @Test
    void retrieveAll() {
        var data = List.of(
                new AppUser("user1", "pswrd1"),
                new AppUser("user2", "pswrd2")
        );
        // GIVEN
        Mockito.when(repository.findAll()).thenReturn(data);
        // WHEN
        List<AppUser> list = sut.retrieveAll();
        // THEN
        assertEquals(2, list.size(), "Taille de list non conforme");
        assertEquals(data, list);
    }

    @Test
    void retrieve_all_empty() {
        List<AppUser> data = List.of();
        // GIVEN
        Mockito.when(repository.findAll()).thenReturn(data);
        // WHEN
        List<AppUser> list = sut.retrieveAll();
        // THEN
        assertTrue(list.isEmpty(), "Taille de list non conforme");
        assertEquals(data, list);
    }

    @Test
    void retrieve() {
        // GIVEN
        AppUser appUser = new AppUser("jean", "naej");
        Mockito.when(repository.findById("jean")).thenReturn(Optional.of(appUser));
        // WHEN
        AppUser result = sut.retrieve("jean");
        // THEN
        assertEquals(appUser.getUsername(), result.getUsername());
        assertEquals(appUser.getPassword(), result.getPassword());
    }

    @Test
    void retrieveNotFount() {
        // GIVEN
        Mockito.when(repository.findById("jean")).thenReturn(Optional.empty());
        // WHEN, THEN
        assertThrows(NotFoundException.class, () -> sut.retrieve("jean"));
    }

    @Test
    void create() {
        // GIVEN
        AppUser appUser = new AppUser("jean", "naej");
        // WHEN
        sut.create(appUser);
        // THEN
        ArgumentCaptor<AppUser> captor = ArgumentCaptor.forClass(AppUser.class);
        Mockito.verify(repository).save(captor.capture());
        AppUser captured = captor.getValue();
        assertEquals("jean", captured.getUsername());
        assertTrue(this.passwordEncoder.matches("naej", captured.getPassword()));
    }

    @Test
    void delete() {
        // GIVEN
        Mockito.when(repository.existsById("jean")).thenReturn(true);
        // WHEN
        sut.delete("jean");
        // THEN
        Mockito.verify(repository).deleteById("jean");
    }
}