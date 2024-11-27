package gtu.cse.se.altefdirt.aymoose.image.internal.domain;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;

@Component
public class ImageFactory {

    public BaseImage createBase(AggregateId relationId, MultipartFile file) {
        return new BaseImage(AggregateId.generate(), relationId, file);
    }

    public Image load(AggregateId id, AggregateId relationId, String filename, Long size, String extension,
            String url) {
        return new Image(id, relationId, filename, size, extension, url);
    }

}