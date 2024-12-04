package gtu.cse.se.altefdirt.aymoose.reservation.internal.infra.adapter.rest.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import gtu.cse.se.altefdirt.aymoose.reservation.internal.application.model.ReservationView;
import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.ReservationStatus;
import lombok.Builder;


@Builder
public record ReservationResponseDTO(
        String id,
        String userId,
        String courtId,
        LocalDate date,
        int hour,
        ReservationStatus status,
        LocalDateTime requestedAt,
        LocalDateTime updatedAt,
        String reserverName) {

    public static ReservationResponseDTO richened(ReservationView view) {

        return ReservationResponseDTO.builder()
                .id(view.id())
                .userId(view.userId())
                .courtId(view.courtId())
                .date(LocalDate.parse(view.date()))
                .hour(view.hour())
                .status(ReservationStatus.valueOf(view.status()))
                .requestedAt(LocalDateTime.parse(view.requestedAt()))
                .updatedAt(LocalDateTime.parse(view.updatedAt()))
                .reserverName(view.reserverName())
                .build();
    }
}
