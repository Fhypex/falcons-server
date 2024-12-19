package gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.handler;

import gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.DeleteCourt;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.CourtRepository;
import gtu.cse.se.altefdirt.aymoose.image.api.provider.ImageProvider;
import gtu.cse.se.altefdirt.aymoose.shared.application.CommandHandler;
import gtu.cse.se.altefdirt.aymoose.shared.application.annotation.RegisterHandler;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import lombok.RequiredArgsConstructor;

@RegisterHandler
@RequiredArgsConstructor
public class DeleteCourtCommandHandler implements CommandHandler<DeleteCourt, Integer> {

    private final CourtRepository repository;
    private final ImageProvider imageProvider;

    @Override
    public Integer handle(DeleteCourt command) {

        imageProvider.deleteByRelationId(AggregateId.fromUUID(command.id()));
        return repository.deleteById(AggregateId.fromUUID(command.id()));
    }
}