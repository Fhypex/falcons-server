package gtu.cse.se.altefdirt.aymoose.reservation.internal.infra.adapter.jpa;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
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
public class ClosedReservationEntity {

    @Id
    private UUID id;
    private UUID courtId;
    private LocalDate date;
    private int hour;
}
