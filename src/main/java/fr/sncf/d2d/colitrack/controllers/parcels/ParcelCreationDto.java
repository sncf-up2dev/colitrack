package fr.sncf.d2d.colitrack.controllers.parcels;

import fr.sncf.d2d.colitrack.domain.parcels.Parcel;
import fr.sncf.d2d.colitrack.domain.users.AppUser;
import jakarta.validation.constraints.NotNull;

public record ParcelCreationDto(
        @NotNull
        String address,
        String postman
) {

    public Parcel toParcel() {
        Parcel parcel = new Parcel();
        parcel.setDestinationAddress(this.address);
        if (this.postman != null) {
            AppUser postman = new AppUser();
            postman.setUsername(this.postman);
            parcel.setPostman(postman);
        }
        return parcel;
    }
}
