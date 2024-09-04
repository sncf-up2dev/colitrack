package fr.sncf.d2d.colitrack.controllers.parcels;

import fr.sncf.d2d.colitrack.domain.parcels.Parcel;

public record ParcelDto(
        String id,
        String address,
        String postman
) {

    public static ParcelDto from(Parcel source) {
        return new ParcelDto(
                source.getId(),
                source.getDestinationAddress(),
                source.getPostman() == null ? null : source.getPostman().getUsername()
        );
    }
}
