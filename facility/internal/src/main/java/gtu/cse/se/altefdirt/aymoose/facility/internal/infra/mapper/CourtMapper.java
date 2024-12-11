package gtu.cse.se.altefdirt.aymoose.facility.internal.infra.mapper;

import org.springframework.stereotype.Component;

import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.Court;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.CourtDetails;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.CourtFactory;
import gtu.cse.se.altefdirt.aymoose.facility.internal.domain.Measurements;
import gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.jpa.CourtEntity;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Capacity;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Price;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CourtMapper {

    private final CourtFactory factory;

    public CourtEntity toEntity(Court court) {
        return CourtEntity.builder()
                .id(court.id().value())
                .userId(court.userId().value())
                .facilityId(court.getFacilityId().value())
                .name(court.details().name())
                .description(court.details().description())
                .height(court.getMeasurements().height())
                .width(court.getMeasurements().width())
                .capacity(court.getCapacity().value())
                .price(court.getPrice().value())
                .build();
    }

    public Court toDomain(CourtEntity entity) {
        return factory.load(AggregateId.fromUUID(entity.getId()),
                AggregateId.fromUUID(entity.getUserId()),
                AggregateId.fromUUID(entity.getFacilityId()),
                new CourtDetails(entity.getName(), entity.getDescription()),
                new Measurements(entity.getHeight(), entity.getWidth()),
                new Capacity(entity.getCapacity()),
                new Price(entity.getPrice()));
    }
}
