package gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter;

import org.springframework.stereotype.Component;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.model.ImageData;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.port.ImageOperationPort;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;

@Component
class ImageOperationAdapter implements ImageOperationPort {

    @Override
    public ImageData find(AggregateId facilityId) {
        return new ImageData("https://d26itsb5vlqdeq.cloudfront.net/image/98CE1963-0E26-F9B2-D4713C65A9683442");
    }

    @Override
    public ImageData save(String image, String relationId, String title) {
        return new ImageData("https://d26itsb5vlqdeq.cloudfront.net/image/98CE1963-0E26-F9B2-D4713C65A9683442");
    }

    @Override
    public void delete(AggregateId imageId) {
        // Delete işlemi yapılabilir
    }
}
