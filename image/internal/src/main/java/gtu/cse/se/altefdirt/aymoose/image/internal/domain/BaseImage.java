package gtu.cse.se.altefdirt.aymoose.image.internal.domain;

import org.springframework.web.multipart.MultipartFile;

import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.BaseAggregateRoot;
import lombok.Getter;

@Getter
public class BaseImage extends BaseAggregateRoot {

    private AggregateId relationId;
    private String name;
    private Long size;
    private String extension;

    public BaseImage(AggregateId id, AggregateId relationId, MultipartFile file) {
        super(id);
        String filteredFilename = file.getOriginalFilename().replaceAll("[^a-zA-Z0-9.-]", "_");
        String extension = filteredFilename.substring(filteredFilename.lastIndexOf('.') + 1);
        Long imageSize = file.getSize();

        if (filteredFilename == null || filteredFilename.isEmpty()) {
            throw new IllegalArgumentException("File name is empty");
        }

        if (filteredFilename.length() > 255) {
            throw new IllegalArgumentException("File name is too long");
        }

        if (imageSize > 10000000 || imageSize < 0) {
            throw new IllegalArgumentException("File size is too large");
        }

        if (!extension.equals("jpg") && !extension.equals("jpeg") && !extension.equals("png")
                && !extension.equals("svg")) {
            throw new IllegalArgumentException("File extension is not supported");
        }

        this.relationId = relationId;
        this.name = filteredFilename;
        this.size = imageSize;
        this.extension = extension;
    }

    public BaseImage(AggregateId id, AggregateId relationId, String name, Long size, String extension) {
        super(id);
        this.relationId = relationId;
        this.name = name;
        this.size = size;
        this.extension = extension;
    }

    public String name() {
        return name;
    }

    public AggregateId relationId() {
        return relationId;
    }

    public Long size() {
        return size;
    }

    public String extension() {
        return extension;
    }
}
