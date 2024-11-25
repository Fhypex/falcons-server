package gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.handler;

import gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.CreateAmenity;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.service.AmenityService;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.Amenity;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.AmenityFactory;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.AmenityRepository;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.model.AmenityView;
import gtu.cse.se.altefdirt.aymoose.shared.application.CommandHandler;
import gtu.cse.se.altefdirt.aymoose.shared.application.annotation.RegisterHandler;
import lombok.RequiredArgsConstructor;

@RegisterHandler
@RequiredArgsConstructor
public class CreateAmenityCommandHandler implements CommandHandler<CreateAmenity, AmenityView> {

    private final AmenityRepository amenityRepository;
    private final AmenityFactory factory;
    private final AmenityService service;

    @Override
    public AmenityView handle(CreateAmenity command) {

        Amenity amenity = factory.create(command.name());
        Amenity savedAmenity = amenityRepository.save(amenity);

        return service.denormalize(savedAmenity);
    }
}
