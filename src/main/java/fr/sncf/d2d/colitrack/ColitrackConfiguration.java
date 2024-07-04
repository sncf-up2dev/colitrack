package fr.sncf.d2d.colitrack;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;
import java.time.ZoneId;

@Configuration
public class ColitrackConfiguration {

    @Bean
    public Clock clock(@Value("${calitrack.zone.id}") String zoneId) {
        return Clock.system(ZoneId.of(zoneId));
    }
}
