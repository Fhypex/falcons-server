package gtu.cse.se.altefdirt.aymoose.image.internal.application.command.handler;

import gtu.cse.se.altefdirt.aymoose.image.internal.application.command.DeleteImage;
import gtu.cse.se.altefdirt.aymoose.image.internal.domain.ImageRepository;
import gtu.cse.se.altefdirt.aymoose.shared.application.CommandHandler;
import gtu.cse.se.altefdirt.aymoose.shared.application.annotation.RegisterHandler;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import lombok.RequiredArgsConstructor;

@RegisterHandler
@RequiredArgsConstructor
class DeleteImageCommandHandler implements CommandHandler<DeleteImage, Integer> {

    private final ImageRepository imageRepository;

    @Override
    public Integer handle(DeleteImage command) {
        return imageRepository.deleteById(AggregateId.fromUUID(command.id()));
    }
}