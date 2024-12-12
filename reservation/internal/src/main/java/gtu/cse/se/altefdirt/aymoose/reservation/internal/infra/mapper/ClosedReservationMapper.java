package gtu.cse.se.altefdirt.aymoose.reservation.internal.infra.mapper;

import org.springframework.stereotype.Component;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.ClosedReservation;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.ClosedReservationFactory;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.infra.adapter.jpa.ClosedReservationEntity;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Date;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ClosedReservationMapper {

    private final ClosedReservationFactory factory;

    public ClosedReservationEntity toEntity(ClosedReservation closedReservation) {
        return ClosedReservationEntity.builder()
                .id(closedReservation.id().value())
                .courtId(closedReservation.courtId().value())
                .date(closedReservation.date().localValue())
                .hour(closedReservation.hour())
                .build();
    }

    public ClosedReservation toDomain(ClosedReservationEntity entity) {
        return factory.load(
                AggregateId.fromUUID(entity.getId()),
                AggregateId.fromUUID(entity.getCourtId()),
                Date.fromLocalDate(entity.getDate()),
                entity.getHour());
    }
}
