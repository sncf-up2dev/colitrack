package fr.sncf.d2d.colitrack.domain.parcels;

import fr.sncf.d2d.colitrack.domain.DomainException;
import fr.sncf.d2d.colitrack.domain.NotFoundException;
import fr.sncf.d2d.colitrack.domain.users.AppUser;
import fr.sncf.d2d.colitrack.domain.users.AppUserRepository;
import fr.sncf.d2d.colitrack.domain.users.AppUserRole;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ParcelService {

    private final ParcelRepository repository;
    private final AppUserRepository appUserRepository;

    public ParcelService(
            ParcelRepository repository,
            AppUserRepository appUserRepository) {
        this.repository = repository;
        this.appUserRepository = appUserRepository;
    }

    public List<Parcel> retrieveAll() {
        return this.repository.findAll();
    }

    public Parcel create(ParcelCreation input) {
        Parcel parcel = new Parcel();
        parcel.setId(UUID.randomUUID().toString());
        parcel.setDestinationAddress(input.address());
        if (input.postman() != null) {
            AppUser postman = this.appUserRepository.findById(input.postman())
                            .orElseThrow(() -> new NotFoundException("Invalid postman username %s.".formatted(input.postman())));
            if (postman.getRole() == AppUserRole.VISITOR) {
                throw new DomainException("Postman cannot be visitor.");
            }
            parcel.setPostman(postman);
        }
        return this.repository.save(parcel);
    }
}
