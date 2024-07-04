package fr.sncf.d2d.colitrack.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Clock;

@RestController
public class ColitrackController {

    private final Clock clock;

    public ColitrackController(Clock clock) {
        this.clock = clock;
    }

    @GetMapping("hello")
    public String sayHello() {
        return "Hey you!";
    }

    @GetMapping("bonjour")
    public String sayBonjour() {
        return "Bonjour toi !";
    }

    @GetMapping("timestamp")
    public TimeDto getTime() {
        long timestamp = clock.millis();
        return new TimeDto(timestamp, "ms", clock.getZone().getId());
    }

    @GetMapping("seconds")
    public TimeDto getTimeSecond() {
        long timestamp = clock.instant().getEpochSecond();
        return new TimeDto(timestamp, "s", clock.getZone().getId());
    }
}
