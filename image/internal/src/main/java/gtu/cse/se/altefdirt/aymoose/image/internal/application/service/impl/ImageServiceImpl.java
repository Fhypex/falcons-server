package gtu.cse.se.altefdirt.aymoose.image.internal.application.service.impl;

import org.springframework.stereotype.Service;

import gtu.cse.se.altefdirt.aymoose.image.internal.application.model.ImageView;
import gtu.cse.se.altefdirt.aymoose.image.internal.application.service.ImageService;
import gtu.cse.se.altefdirt.aymoose.image.internal.domain.Image;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
class ImageServiceImpl implements ImageService {
    
    @Override
    public ImageView denormalize(Image image, String url)  {       
        return new ImageView(image, url);
    }
}
