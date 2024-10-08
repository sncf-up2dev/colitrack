package fr.sncf.d2d.colitrack.domain.parcels;

import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface ParcelRepository extends ListCrudRepository<Parcel, String> {

    List<Parcel> findByPostmanUsername(String username);
}
