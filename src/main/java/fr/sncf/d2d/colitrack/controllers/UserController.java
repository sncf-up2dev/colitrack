package fr.sncf.d2d.colitrack.controllers;

import fr.sncf.d2d.colitrack.domain.AppUser;
import fr.sncf.d2d.colitrack.domain.AppUserService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final AppUserService service;

    public UserController(AppUserService service) {
        this.service = service;
    }

    @GetMapping
    public List<AppUserDto> retrieveAll() {
        return this.service.retrieveAll().stream()
                .map(AppUserDto::from)
                .toList();
    }

    @GetMapping("/{id}")
    public AppUserDto retrieve(
            @PathVariable String id
    ) {
        AppUser appUser = this.service.retrieve(id);
        return AppUserDto.from(appUser);
    }

    @PostMapping
    public AppUserDto create(
            @RequestBody AppUserCreationDto input
    ) {
        AppUser appUser = input.toAppUser();
        appUser = this.service.create(appUser);
        return AppUserDto.from(appUser);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable String id
    ) {
        this.service.delete(id);
    }
}
