package gtu.cse.se.altefdirt.aymoose.account.internal.infra.adapter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import gtu.cse.se.altefdirt.aymoose.account.internal.application.model.ImageData;
import gtu.cse.se.altefdirt.aymoose.account.internal.application.port.ImageOperationsPort;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;

@Component
class ImageOperationsAdapter implements ImageOperationsPort {

    @Autowired
    @Value("${image.name}")
    String imageName;

    @Override
    public void deleteImage(AggregateId imageId) {
        // TODO Auto-generated method stub

    }

    @Override
    public void saveProfileImage(String image) {
        // TODO Auto-generated method stub

    }

    @Override
    public ImageData getImage(AggregateId imageId) {
        // TODO Auto-generated method stub
        return new ImageData(imageName, "mocked-image-title");
    }
}
