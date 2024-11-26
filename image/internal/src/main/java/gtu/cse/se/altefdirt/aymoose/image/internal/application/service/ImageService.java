package gtu.cse.se.altefdirt.aymoose.image.internal.application.service;

import gtu.cse.se.altefdirt.aymoose.image.internal.application.model.ImageView;
import gtu.cse.se.altefdirt.aymoose.image.internal.domain.Image;


public interface ImageService {
    
    ImageView denormalize(Image image, String url);
}
