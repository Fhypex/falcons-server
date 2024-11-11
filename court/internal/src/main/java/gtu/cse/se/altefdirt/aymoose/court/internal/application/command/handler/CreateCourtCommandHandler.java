package gtu.cse.se.altefdirt.aymoose.court.internal.application.command.handler;

import java.util.stream.Collectors;

import gtu.cse.se.altefdirt.aymoose.court.internal.application.command.CreateCourt;
import gtu.cse.se.altefdirt.aymoose.court.internal.application.model.CourtView;
import gtu.cse.se.altefdirt.aymoose.court.internal.application.port.AmenityOperationsPort;
import gtu.cse.se.altefdirt.aymoose.court.internal.application.port.ImageOperationsPort;
import gtu.cse.se.altefdirt.aymoose.court.internal.application.service.CourtService;
import gtu.cse.se.altefdirt.aymoose.court.internal.domain.Amenity;
import gtu.cse.se.altefdirt.aymoose.court.internal.domain.Capacity;
import gtu.cse.se.altefdirt.aymoose.court.internal.domain.Court;
import gtu.cse.se.altefdirt.aymoose.court.internal.domain.CourtDetails;
import gtu.cse.se.altefdirt.aymoose.court.internal.domain.CourtFactory;
import gtu.cse.se.altefdirt.aymoose.court.internal.domain.CourtRepository;
import gtu.cse.se.altefdirt.aymoose.court.internal.domain.Measurements;
import gtu.cse.se.altefdirt.aymoose.court.internal.domain.WorkHours;
import gtu.cse.se.altefdirt.aymoose.shared.application.CommandHandler;
import gtu.cse.se.altefdirt.aymoose.shared.application.annotation.RegisterHandler;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Location;
import lombok.RequiredArgsConstructor;


@RegisterHandler
@RequiredArgsConstructor
public class CreateCourtCommandHandler implements CommandHandler<CreateCourt, CourtView> {

    private final CourtFactory factory;
    private final AmenityOperationsPort amenityOperationsPort;
    private final CourtService service;
    private final ImageOperationsPort imageOperationPort;
    private final CourtRepository courtRepository;

    @Override
    public CourtView handle(CreateCourt command) {

        if (!amenityOperationsPort.exists(command.amenityIds())) {
            throw new IllegalArgumentException("Amenities do not exist");
        }

        Court court = factory.create(AggregateId.from(command.facilityId()),
                                     new CourtDetails(command.name(), command.description()),
                                     new Measurements(command.height(), command.width()),
                                     new Capacity(command.capacity()),
                                     new WorkHours(command.openTime(), command.closeTime()),
                                     new Location(command.latitude(), command.longitude()),
                                     command.amenityIds().stream().map(amenity -> new Amenity(AggregateId.from(amenity))).collect(Collectors.toList()));
        
        Court savedCourt = courtRepository.save(court);
        command.images().forEach(image -> imageOperationPort.save(image, savedCourt.id().value(), "somerandomimagename"));
        return service.denormalize(savedCourt);
    }
}