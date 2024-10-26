package gtu.cse.se.altefdirt.aymoose.court.internal.infra.adapter.jpa;

import gtu.cse.se.altefdirt.aymoose.court.internal.domain.Amenity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String amenityId;

    public static AmenityEntity from(Amenity amenity) {
        return AmenityEntity.builder()
            .amenityId(amenity.amenityId().value())
            .build();
    }
}
