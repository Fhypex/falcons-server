package gtu.cse.se.altefdirt.aymoose.reservation.internal.infra.adapter.jpa;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

import gtu.cse.se.altefdirt.aymoose.reservation.internal.domain.ReservationStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ReservationEntity {

    @Id
    private UUID id;
    private UUID userId;
    private UUID courtId;
    private LocalDate date;
    private int hour;
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;
    private Instant requestedAt;
    private Instant updatedAt;
}
