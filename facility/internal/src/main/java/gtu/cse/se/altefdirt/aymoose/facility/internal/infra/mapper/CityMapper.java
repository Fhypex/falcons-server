package gtu.cse.se.altefdirt.aymoose.facility.internal.infra.mapper;

import org.springframework.stereotype.Component;

import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.City;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.CityFactory;
import gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.jpa.CityEntity;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CityMapper {

    private final CityFactory factory;

    public CityEntity toEntity(City city) {
        return CityEntity.builder()
                .id(city.id())
                .name(city.name())
                .build();
    }

    public City toDomain(CityEntity entity) {
        return factory.load(
                entity.getId(),
                entity.getName());
    }
}
