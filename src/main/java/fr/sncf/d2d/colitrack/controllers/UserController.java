package fr.sncf.d2d.colitrack.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final List<AppUser> data = new ArrayList<>();

    @GetMapping
    public List<AppUserDto> retrieveAll() {
        return this.data.stream()
                .map(AppUserDto::from)
                .toList();
    }

    @GetMapping("/{id}")
    public AppUserDto retrieve(
            @PathVariable String id
    ) {
        return this.data.stream()
                .filter(appUser -> id.equals(appUser.getUsername()))
                .findAny()
                .map(AppUserDto::from)
                .orElseThrow(() -> new IllegalStateException("User not found"));
    }

    @PostMapping
    public AppUserDto create(
            @RequestBody AppUserCreationDto input
    ) {
        AppUser appUser = input.toAppUser();
        this.data.add(appUser);
        return AppUserDto.from(appUser);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable String id
    ) {
        this.data.removeIf(appUser -> id.equals(appUser.getUsername()));
    }
}
