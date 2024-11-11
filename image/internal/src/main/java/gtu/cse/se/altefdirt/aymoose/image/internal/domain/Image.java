package gtu.cse.se.altefdirt.aymoose.image.internal.domain;

import org.springframework.web.multipart.MultipartFile;

import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.BaseAggregateRoot;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Image extends BaseAggregateRoot
{
    private AggregateId relationId;
    private MultipartFile file;
    private String extension;


    public Image(AggregateId id , AggregateId relationId, MultipartFile file , String extension){
        super(id);
        this.relationId = relationId;
        this.file = file;
        this.extension = extension;
        
    }
}

