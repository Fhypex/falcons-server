package gtu.cse.se.altefdirt.aymoose.image.internal.application.command.handler;



import gtu.cse.se.altefdirt.aymoose.image.internal.application.command.CreateImage;
import gtu.cse.se.altefdirt.aymoose.image.internal.application.model.ImageView;
import gtu.cse.se.altefdirt.aymoose.image.internal.application.service.ImageService;
import gtu.cse.se.altefdirt.aymoose.image.internal.domain.Image;

import gtu.cse.se.altefdirt.aymoose.image.internal.domain.ImageFactory;
import gtu.cse.se.altefdirt.aymoose.image.internal.domain.ImageRepository;

import gtu.cse.se.altefdirt.aymoose.shared.application.CommandHandler;
import gtu.cse.se.altefdirt.aymoose.shared.application.annotation.RegisterHandler;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import lombok.RequiredArgsConstructor;


@RegisterHandler
@RequiredArgsConstructor
public class CreateImageCommandHandler implements CommandHandler<CreateImage, ImageView> {

    private final ImageFactory factory;
    private final ImageService service;
    private final ImageRepository ImageRepository;

    @Override
    public ImageView handle(CreateImage command) {

        Image Image = factory.create(AggregateId.from(command.relationId()), command.file() , command.extension());
        System.out.println("asd");
        service.storeImage(Image);                                                              
        Image savedImage = ImageRepository.save(Image);
        return service.denormalize(savedImage);
    }
}