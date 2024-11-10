package gtu.cse.se.altefdirt.aymoose.image.internal.infra.adapter.jpa;

import java.time.Instant;

import gtu.cse.se.altefdirt.aymoose.image.internal.domain.Image;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class ImageEntity {
    
    @Id
    private String id;
    private String relationId;
    private String imageId;
    private String extension;
    
    

    public static ImageEntity from(Image image) {
        return ImageEntity.builder()
            .id(image.id().value())
            .relationId(image.getRelationId().value()) 
            .imageId(image.id().value())
            .extension(image.getExtension())          
            .build();
    }
}
