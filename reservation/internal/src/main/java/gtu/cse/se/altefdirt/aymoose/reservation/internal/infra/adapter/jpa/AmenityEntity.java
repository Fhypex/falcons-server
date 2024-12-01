package gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.jpa;

import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.Amenity;
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
public class AmenityEntity {
    
    @Id
    private String id;
    private String name;

    public static AmenityEntity from(Amenity amenity) {
        return AmenityEntity.builder()
            .id(amenity.id().value())
            .name(amenity.name())
            .build();
    }
}
