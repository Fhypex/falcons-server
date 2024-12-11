package gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.handler;

import java.util.Optional;
import java.util.UUID;

import gtu.cse.se.altefdirt.aymoose.facility.internal.application.command.UpdateCourt;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.Court;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.CourtRepository;
import gtu.cse.se.altefdirt.aymoose.image.api.provider.ImageProvider;
import gtu.cse.se.altefdirt.aymoose.shared.application.CommandHandler;
import gtu.cse.se.altefdirt.aymoose.shared.application.annotation.RegisterHandler;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import lombok.RequiredArgsConstructor;

@RegisterHandler
@RequiredArgsConstructor
public class UpdateCourtCommandHandler implements CommandHandler<UpdateCourt, Court> {

    private final CourtRepository repository;
    private final ImageProvider imageProvider;

    @Override
    public Court handle(UpdateCourt command) {

        Optional<Court> fetch = repository.findById(AggregateId.fromUUID(command.id()));

        if (fetch.isEmpty()) {
            throw new IllegalArgumentException("Court does not exist");
        }

        Court court = fetch.get();

        if (command.name() != null) {
            court.updateName(command.name());
        }
        if (command.description() != null) {
            court.updateDescription(command.description());
        }
        if (command.height() != null) {
            court.updateHeight(command.height());
        }
        if (command.width() != null) {
            court.updateWidth(command.width());
        }
        if (command.capacity() != null) {
            court.updateCapacity(command.capacity());
        }
        if (command.price() != null) {
            court.updatePrice(command.price());
        }

        if (command.deletedImages() != null) {
            for (UUID image : command.deletedImages()) {
                imageProvider.deleteById(AggregateId.fromUUID(image));
            }
        }

        if (command.newImages() != null) {
            for (var image : command.newImages()) {
                imageProvider.save(court.id(), image);
            }
        }

        return repository.save(court);
    }
}