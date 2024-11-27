package gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.handler;

import gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.CreateAmenity;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.Amenity;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.AmenityFactory;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.AmenityRepository;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.port.ImageOperationPort;
import gtu.cse.se.altefdirt.aymoose.shared.application.CommandHandler;
import gtu.cse.se.altefdirt.aymoose.shared.application.ImageData;
import gtu.cse.se.altefdirt.aymoose.shared.application.annotation.RegisterHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RegisterHandler
@RequiredArgsConstructor
@Slf4j
public class CreateAmenityCommandHandler implements CommandHandler<CreateAmenity, Amenity> {

    private final AmenityRepository amenityRepository;
    private final AmenityFactory factory;
    private final ImageOperationPort imageOperationPort;

    @Override
    public Amenity handle(CreateAmenity command) {

        Amenity amenity = factory.create(command.name());
        Amenity savedAmenity = amenityRepository.save(amenity);

        log.info("Amenity saved: {}", savedAmenity);
        ImageData imageData = imageOperationPort.save(savedAmenity.id(), command.image());
        log.info("Image saved: {}", imageData);
        return savedAmenity;
    }
}
