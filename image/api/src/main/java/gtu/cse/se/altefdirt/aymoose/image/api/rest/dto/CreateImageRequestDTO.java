package gtu.cse.se.altefdirt.aymoose.image.api.rest.dto;

import org.apache.commons.lang3.Validate;

public record CreateImageRequestDTO(
        String relationId
        
        ) 
{   
        
    public CreateImageRequestDTO (String relationId) 
    {
        Validate.notNull(relationId, "Relation ID cannot be null");
        
        Validate.isTrue(relationId.length() == 36, "Invalid relation ID");
       
        this.relationId = relationId;
    
        
    }
}