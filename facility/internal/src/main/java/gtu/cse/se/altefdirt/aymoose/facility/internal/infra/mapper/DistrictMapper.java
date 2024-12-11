package gtu.cse.se.altefdirt.aymoose.facility.internal.infra.mapper;

import org.springframework.stereotype.Component;

import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.District;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.DistrictFactory;
import gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.jpa.DistrictEntity;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DistrictMapper {

    private final DistrictFactory factory;

    public DistrictEntity toEntity(District district) {
        return DistrictEntity.builder()
                .id(district.id())
                .name(district.name())
                .cityId(district.cityId())
                .build();
    }

    public District toDomain(DistrictEntity entity) {
        return factory.load(
                entity.getId(),
                entity.getCityId(),
                entity.getName());
    }
}
