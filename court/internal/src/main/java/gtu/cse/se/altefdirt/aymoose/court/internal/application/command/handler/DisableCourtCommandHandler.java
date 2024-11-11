package gtu.cse.se.altefdirt.aymoose.court.internal.application.command.handler;

import java.util.Optional;

import gtu.cse.se.altefdirt.aymoose.court.internal.application.command.DisableCourt;
import gtu.cse.se.altefdirt.aymoose.court.internal.application.model.CourtView;
import gtu.cse.se.altefdirt.aymoose.court.internal.application.service.CourtService;
import gtu.cse.se.altefdirt.aymoose.court.internal.domain.Court;
import gtu.cse.se.altefdirt.aymoose.court.internal.domain.CourtRepository;
import gtu.cse.se.altefdirt.aymoose.shared.application.CommandHandler;
import gtu.cse.se.altefdirt.aymoose.shared.application.annotation.RegisterHandler;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import lombok.RequiredArgsConstructor;

@RegisterHandler
@RequiredArgsConstructor
public class DisableCourtCommandHandler implements CommandHandler<DisableCourt, CourtView> {

    private final CourtService service;
    private final CourtRepository courtRepository;

    @Override
    public CourtView handle(DisableCourt command) {
        Optional<Court> fetch = courtRepository.findById(AggregateId.from(command.courtId()));
        if (fetch.isEmpty()) {
            throw new IllegalArgumentException("Court does not exist");
        }
        Court court = fetch.get();
        court.disable();
        return service.denormalize(courtRepository.save(court));
    }
}