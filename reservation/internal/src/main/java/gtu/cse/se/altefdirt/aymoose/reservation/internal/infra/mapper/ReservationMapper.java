package gtu.cse.se.altefdirt.aymoose.reservation.internal.infra.mapper;

import org.springframework.stereotype.Component;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.Reservation;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.ReservationFactory;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.infra.adapter.jpa.ReservationEntity;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Date;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReservationMapper {

    private final ReservationFactory factory;

    public ReservationEntity toEntity(Reservation reservation) {
        return ReservationEntity.builder()
                .id(reservation.id().value())
                .userId(reservation.userId().value())
                .courtId(reservation.courtId().value())
                .date(reservation.date().localValue())
                .hour(reservation.hour())
                .status(reservation.status())
                .requestedAt(reservation.requestedAt())
                .updatedAt(reservation.updatedAt())
                .build();
    }

    public Reservation toDomain(ReservationEntity entity) {
        return factory.load(
                AggregateId.fromUUID(entity.getId()),
                AggregateId.fromUUID(entity.getUserId()),
                AggregateId.fromUUID(entity.getCourtId()),
                Date.fromLocalDate(entity.getDate()),
                entity.getHour(),
                entity.getStatus(),
                entity.getRequestedAt(),
                entity.getUpdatedAt());
    }
}
