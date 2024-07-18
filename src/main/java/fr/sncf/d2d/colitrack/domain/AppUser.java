package fr.sncf.d2d.colitrack.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class AppUser {

    @Id
    private String username;

    private String password;

    public AppUser() {
    }

    public AppUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
