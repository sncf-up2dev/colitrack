package fr.sncf.d2d.colitrack.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AppUserServiceTest {

    @Mock
    AppUserRepository repository;

    @InjectMocks
    AppUserService sut;

    @Test
    void retrieveAll() {
        var data = List.of(
                new AppUser("user1", "pswrd1"),
                new AppUser("user2", "pswrd2")
        );
        // GIVEN
        Mockito.when(repository.find()).thenReturn(data);
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
        Mockito.when(repository.find()).thenReturn(data);
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
        assertEquals(appUser, captured);
    }

    @Test
    void delete() {
        // WHEN
        sut.delete("jean");
        // THEN
        Mockito.verify(repository).delete("jean");
    }
}