package fr.sncf.d2d.colitrack.domain.parcels;

import fr.sncf.d2d.colitrack.domain.users.AppUser;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Parcel {

    @Id
    private String id;

    @Column(nullable = false)
    private String destinationAddress;

    @ManyToOne
    private AppUser postman;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public AppUser getPostman() {
        return postman;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public void setPostman(AppUser postman) {
        this.postman = postman;
    }
}
