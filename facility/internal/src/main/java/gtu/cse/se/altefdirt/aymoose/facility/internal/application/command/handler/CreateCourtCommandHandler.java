package gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.handler;

import gtu.cse.se.altefdirt.aymoose.facility.internal.application.port.ImageOperationPort;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.CreateCourt;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.Court;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.CourtDetails;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.CourtFactory;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.CourtRepository;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.Measurements;
import gtu.cse.se.altefdirt.aymoose.shared.application.CommandHandler;
import gtu.cse.se.altefdirt.aymoose.shared.application.annotation.RegisterHandler;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Capacity;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Price;
import lombok.RequiredArgsConstructor;

@RegisterHandler
@RequiredArgsConstructor
public class CreateCourtCommandHandler implements CommandHandler<CreateCourt, Court> {

    private final CourtFactory factory;
    private final ImageOperationPort imageOperationPort;
    private final CourtRepository courtRepository;

    @Override
    public Court handle(CreateCourt command) {

        Court court = factory.create(
                AggregateId.fromUUID(command.userId()),
                AggregateId.fromUUID(command.facilityId()),
                new CourtDetails(command.name(), command.description()),
                new Measurements(command.height(), command.width()),
                new Capacity(command.capacity()),
                new Price(command.price()));

        Court savedCourt = courtRepository.save(court);
        command.images().forEach(image -> imageOperationPort.save(savedCourt.id(), image));
        return savedCourt;
    }
}