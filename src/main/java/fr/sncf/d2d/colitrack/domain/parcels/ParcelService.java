package fr.sncf.d2d.colitrack.domain.parcels;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ParcelService {

    private final ParcelRepository repository;

    public ParcelService(ParcelRepository repository) {
        this.repository = repository;
    }

    public List<Parcel> retrieveAll() {
        return this.repository.findAll();
    }

    public Parcel create(Parcel input) {
        input.setId(UUID.randomUUID().toString());
        return this.repository.save(input);
    }
}
