package gtu.cse.se.altefdirt.aymoose.image.internal.infra.adapter.jpa;

import java.util.UUID;

import gtu.cse.se.altefdirt.aymoose.image.internal.domain.BaseImage;
import gtu.cse.se.altefdirt.aymoose.image.internal.domain.Image;
import jakarta.persistence.Column;
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
    private UUID id;
    private UUID relationId;
    @Column(length = 512)
    private String url;
    private String name;
    private Long size;
    private String extension;
}
