package gtu.cse.se.altefdirt.aymoose.court.internal.application.command.handler;

import org.apache.commons.lang3.NotImplementedException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;

import gtu.cse.se.altefdirt.aymoose.court.internal.application.command.UpdateCourt;
import gtu.cse.se.altefdirt.aymoose.court.internal.application.model.CourtView;
import gtu.cse.se.altefdirt.aymoose.court.internal.application.port.FacilityOperationPort;
import gtu.cse.se.altefdirt.aymoose.court.internal.application.port.ImageOperationsPort;
import gtu.cse.se.altefdirt.aymoose.court.internal.application.service.CourtService;
import gtu.cse.se.altefdirt.aymoose.court.internal.domain.Court;
import gtu.cse.se.altefdirt.aymoose.court.internal.domain.CourtFactory;
import gtu.cse.se.altefdirt.aymoose.court.internal.domain.CourtRepository;
import gtu.cse.se.altefdirt.aymoose.shared.application.CommandHandler;
import gtu.cse.se.altefdirt.aymoose.shared.application.annotation.RegisterHandler;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import lombok.RequiredArgsConstructor;

@RegisterHandler
@RequiredArgsConstructor
public class UpdateCourtCommandHandler implements CommandHandler<UpdateCourt, CourtView> {

    private final CourtFactory factory;
    private final CourtService service;
    private final FacilityOperationPort facilityOperationPort;
    private final ImageOperationsPort imageOperationPort;
    private final CourtRepository repository;

    @Override
    public CourtView handle(UpdateCourt command) {
        
        Optional<Court> fetch = repository.findById(AggregateId.from(command.id()));

        if (fetch.isEmpty()) {
            throw new IllegalArgumentException("Court does not exist");
        }

        Court court = fetch.get();

        court.updateDetails(command.name(), command.description());
        court.updateMeasurements(command.height(), command.width());
        court.updateCapacity(command.capacity());

        Court savedCourt = repository.save(court);

        return service.denormalize(savedCourt);
    }
}