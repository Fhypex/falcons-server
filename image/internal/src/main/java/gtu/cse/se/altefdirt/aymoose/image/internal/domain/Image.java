package gtu.cse.se.altefdirt.aymoose.image.internal.domain;

import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import lombok.Getter;

@Getter
public class Image extends BaseImage {

    private String url;

    public Image(AggregateId id, AggregateId relationId, String name, Long size, String extension, String url) {
        super(id, relationId, name, size, extension);
        this.url = url;
    }

    public String url() {
        return url;
    }
}
