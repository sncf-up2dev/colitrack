package fr.sncf.d2d.colitrack.controllers.parcels;

import fr.sncf.d2d.colitrack.domain.parcels.Parcel;
import fr.sncf.d2d.colitrack.domain.parcels.ParcelService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/parcels")
public class ParcelController {

    private final ParcelService service;

    public ParcelController(ParcelService service) {
        this.service = service;
    }

    @PostMapping
    public ParcelDto create(
            @RequestBody
            ParcelCreationDto creation
    ) {
        Parcel toCreate = creation.toParcel();
        Parcel created = this.service.create(toCreate);
        return ParcelDto.from(created);
    }

    @GetMapping
    public List<ParcelDto> retrieveAll() {
        return this.service.retrieveAll().stream()
                .map(ParcelDto::from)
                .toList();
    }
}
