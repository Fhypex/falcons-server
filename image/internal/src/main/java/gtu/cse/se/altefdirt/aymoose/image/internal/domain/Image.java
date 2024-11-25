package gtu.cse.se.altefdirt.aymoose.image.internal.domain;

import org.springframework.web.multipart.MultipartFile;

import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.BaseAggregateRoot;
import lombok.Getter;


@Getter
public class Image extends BaseAggregateRoot {

    private AggregateId relationId;
    private String name;
    private Long size;
    private String extension;
    private String url;

    public static Image newlyCreated(AggregateId id, AggregateId relationId, MultipartFile file) {
        
        String filteredFilename = file.getOriginalFilename().replaceAll("[^a-zA-Z0-9.-]", "_");
        String extension = filteredFilename.substring(filteredFilename.lastIndexOf('.') + 1);
        Long size = file.getSize();

        if(filteredFilename == null || filteredFilename.isEmpty()) {
            throw new IllegalArgumentException("File name is empty");
        }

        if(filteredFilename.length() > 255) {
            throw new IllegalArgumentException("File name is too long");
        }

        if(size > 10000000 || size < 0) {
            throw new IllegalArgumentException("File size is too large");
        }

        if(!extension.equals("jpg") && !extension.equals("jpeg") && !extension.equals("png")) {
            throw new IllegalArgumentException("File extension is not supported");
        }
        return new Image(id, relationId, filteredFilename, size, extension, null);
    }

    public Image(AggregateId id, AggregateId relationId, String name, Long size, String extension, String url) {
        super(id);
        this.relationId = relationId;
        this.name = name;
        this.size = size;
        this.extension = extension;
        this.url = url;
    }

    public String name() {
        return name;
    }

    public AggregateId getRelationId() {
        return relationId;
    }

    public Long getSize() {
        return size;
    }

    public String getExtension() {
        return extension;
    }

    public String getUrl() {
        return url;
    }
}

