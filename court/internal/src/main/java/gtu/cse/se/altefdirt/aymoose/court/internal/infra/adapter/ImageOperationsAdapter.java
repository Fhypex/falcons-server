package gtu.cse.se.altefdirt.aymoose.court.internal.infra.adapter;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import gtu.cse.se.altefdirt.aymoose.court.internal.application.model.ImageData;
import gtu.cse.se.altefdirt.aymoose.court.internal.application.port.ImageOperationsPort;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;

@Component
class ImageOperationsAdapter implements ImageOperationsPort {
    
    @Override
    public List<ImageData> findAll(AggregateId courtId) {
        return List.of(new ImageData("some-mocked-image", "some-mocked-title"));
    }

    @Override
    public ImageData save(String image, String relationId, String title) {
        return new ImageData("some-mocked-image", "some-mocked-title");
    }

    @Override
    public void delete(AggregateId imageId) {
        // do nothing
    }
}