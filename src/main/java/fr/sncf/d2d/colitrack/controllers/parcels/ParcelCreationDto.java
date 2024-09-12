package fr.sncf.d2d.colitrack.controllers.parcels;

import fr.sncf.d2d.colitrack.domain.parcels.ParcelCreation;
import jakarta.validation.constraints.NotNull;

public record ParcelCreationDto(
        @NotNull
        String address,
        String postman
) {

    public ParcelCreation toParcelCreation() {
        return new ParcelCreation(this.address, this.postman);
    }
}
