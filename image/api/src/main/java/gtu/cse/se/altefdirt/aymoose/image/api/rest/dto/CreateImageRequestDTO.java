package gtu.cse.se.altefdirt.aymoose.image.api.rest.dto;

import org.apache.commons.lang3.Validate;
import org.springframework.web.multipart.MultipartFile;

public record CreateImageRequestDTO(
        String relationId,
        MultipartFile file,
        String extension
        
        ) 
{   
        
    public CreateImageRequestDTO (String relationId , MultipartFile file, String extension) 
    {
        Validate.notNull(relationId, "Relation ID cannot be null");
        
        Validate.isTrue(relationId.length() == 36, "Invalid relation ID");
       
        this.relationId = relationId;
        this.file = file;
        this.extension = extension;
    
        
    }
}