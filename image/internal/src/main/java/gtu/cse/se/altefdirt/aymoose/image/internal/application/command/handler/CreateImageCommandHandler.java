package gtu.cse.se.altefdirt.aymoose.image.internal.application.command.handler;

import gtu.cse.se.altefdirt.aymoose.image.internal.application.command.CreateImage;
import gtu.cse.se.altefdirt.aymoose.image.internal.domain.BaseImage;
import gtu.cse.se.altefdirt.aymoose.image.internal.domain.Image;

import gtu.cse.se.altefdirt.aymoose.image.internal.domain.ImageFactory;
import gtu.cse.se.altefdirt.aymoose.image.internal.domain.ImageRepository;

import gtu.cse.se.altefdirt.aymoose.shared.application.CommandHandler;
import gtu.cse.se.altefdirt.aymoose.shared.application.annotation.RegisterHandler;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RegisterHandler
@RequiredArgsConstructor
class CreateImageCommandHandler implements CommandHandler<CreateImage, Image> {

    private final ImageFactory factory;
    private final ImageRepository imageRepository;

    @Override
    public Image handle(CreateImage command) {

        BaseImage baseImage = factory.createBase(AggregateId.from(command.relationId()), command.file());
        return imageRepository.save(baseImage, command.file());
    }
}