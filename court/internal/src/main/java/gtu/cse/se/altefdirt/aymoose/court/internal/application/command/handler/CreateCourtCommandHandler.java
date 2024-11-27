package gtu.cse.se.altefdirt.aymoose.court.internal.application.command.handler;

import gtu.cse.se.altefdirt.aymoose.court.internal.application.command.CreateCourt;
import gtu.cse.se.altefdirt.aymoose.court.internal.application.model.CourtView;
import gtu.cse.se.altefdirt.aymoose.court.internal.application.port.FacilityOperationPort;
import gtu.cse.se.altefdirt.aymoose.court.internal.application.port.ImageOperationPort;
import gtu.cse.se.altefdirt.aymoose.court.internal.application.service.CourtService;
import gtu.cse.se.altefdirt.aymoose.court.internal.domain.Court;
import gtu.cse.se.altefdirt.aymoose.court.internal.domain.CourtDetails;
import gtu.cse.se.altefdirt.aymoose.court.internal.domain.CourtFactory;
import gtu.cse.se.altefdirt.aymoose.court.internal.domain.CourtRepository;
import gtu.cse.se.altefdirt.aymoose.court.internal.domain.Measurements;

import gtu.cse.se.altefdirt.aymoose.shared.application.CommandHandler;
import gtu.cse.se.altefdirt.aymoose.shared.application.annotation.RegisterHandler;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Capacity;
import lombok.RequiredArgsConstructor;

@RegisterHandler
@RequiredArgsConstructor
public class CreateCourtCommandHandler implements CommandHandler<CreateCourt, CourtView> {

    private final CourtFactory factory;
    private final CourtService service;
    private final ImageOperationPort imageOperationPort;
    private final CourtRepository courtRepository;
    private final FacilityOperationPort facilityOperationPort;

    @Override
    public CourtView handle(CreateCourt command) {

        if (!facilityOperationPort.existsById(command.facilityId())) {
            throw new IllegalArgumentException("Facility not found");
        }

        Court court = factory.create(AggregateId.from(command.facilityId()),
                new CourtDetails(command.name(), command.description()),
                new Measurements(command.height(), command.width()),
                new Capacity(command.capacity()));

        Court savedCourt = courtRepository.save(court);
        command.images().forEach(image -> imageOperationPort.save(savedCourt.id(), image));
        return service.denormalize(savedCourt);
    }
}