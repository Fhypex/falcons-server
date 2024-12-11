package gtu.cse.se.altefdirt.aymoose.reservation.internal.infra.mapper;

import org.springframework.stereotype.Component;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.LocalReservation;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.infra.adapter.jpa.LocalReservationEntity;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;

@Component
public class LocalReservationMapper {

    public LocalReservationEntity toEntity(LocalReservation localReservation) {
        return LocalReservationEntity.builder()
                .id(localReservation.id().value())
                .courtId(localReservation.courtId().value())
                .name(localReservation.name())
                .phoneNumber(localReservation.phoneNumber())
                .date(localReservation.date())
                .hour(localReservation.hour())
                .build();
    }

    public LocalReservation toDomain(LocalReservationEntity entity) {
        return new LocalReservation(
                AggregateId.fromUUID(entity.getId()),
                AggregateId.fromUUID(entity.getCourtId()),
                entity.getName(),
                entity.getPhoneNumber(),
                entity.getDate(),
                entity.getHour());
    }
}
