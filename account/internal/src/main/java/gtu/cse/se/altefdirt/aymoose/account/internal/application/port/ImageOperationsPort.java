package gtu.cse.se.altefdirt.aymoose.account.internal.application.port;

import gtu.cse.se.altefdirt.aymoose.account.internal.application.model.ImageData;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;

public interface ImageOperationsPort {
 
    void deleteImage(AggregateId imageId);

    void saveProfileImage(String image);

    public ImageData getImage(AggregateId imageId);
}
