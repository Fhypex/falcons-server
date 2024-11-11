package gtu.cse.se.altefdirt.aymoose.image.internal.application.service;

import org.springframework.core.io.Resource;

import gtu.cse.se.altefdirt.aymoose.image.internal.application.model.ImageView;
import gtu.cse.se.altefdirt.aymoose.image.internal.domain.Image;


public interface ImageService {
    
    ImageView denormalize(Image image);
    public String storeImage(Image image);
    public Resource loadImageAsResource(String fileName , String extension);
}
