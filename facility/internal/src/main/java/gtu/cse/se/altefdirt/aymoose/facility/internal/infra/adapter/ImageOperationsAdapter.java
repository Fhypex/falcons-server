package gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter;

import java.util.List;
import org.springframework.stereotype.Component;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.model.ImageData;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.port.ImageOperationsPort;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;

@Component
class ImageOperationsAdapter implements ImageOperationsPort {

    @Override
    public List<ImageData> findAll(AggregateId facilityId) {
        return List.of(new ImageData("mocked-facility-image", "mocked-title"));
    }

    @Override
    public ImageData save(String image, String relationId, String title) {
        return new ImageData("mocked-facility-image", "mocked-title");
    }

    @Override
    public void delete(AggregateId imageId) {
        // Delete işlemi yapılabilir
    }
}

