package gtu.cse.se.altefdirt.aymoose.facility.internal.infra.mapper;

import org.springframework.stereotype.Component;

import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.Amenity;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.AmenityFactory;
import gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.jpa.AmenityEntity;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AmenityMapper {

    private final AmenityFactory factory;

    public AmenityEntity toEntity(Amenity amenity) {
        return AmenityEntity.builder()
                .id(amenity.id().value())
                .name(amenity.name())
                .build();
    }

    public Amenity toDomain(AmenityEntity entity) {
        return factory.load(
                AggregateId.fromUUID(entity.getId()),
                entity.getName());
    }
}
