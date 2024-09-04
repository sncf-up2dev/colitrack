package fr.sncf.d2d.colitrack.domain.parcels;

import org.springframework.data.repository.ListCrudRepository;

public interface ParcelRepository extends ListCrudRepository<Parcel, String> {
}
