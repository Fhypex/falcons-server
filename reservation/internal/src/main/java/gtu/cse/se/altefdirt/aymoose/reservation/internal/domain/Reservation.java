package gtu.cse.se.altefdirt.aymoose.reservation.internal.domain;

import lombok.Getter;

import java.time.Instant;
import java.time.LocalDate;

import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.BaseAggregateRoot;


@Getter
public class Reservation extends BaseAggregateRoot {

    private AggregateId userId;
    private AggregateId courtId;
    private LocalDate date;
    private int hour;
    private ReservationStatus status;
    private Instant requestedAt;
    private Instant updatedAt;


    public Reservation(AggregateId id,
            AggregateId userId,
            AggregateId courtId,
            LocalDate date,
            int hour,
            ReservationStatus status,
            Instant requestedAt,
            Instant updatedAt) {
        super(id);
        this.userId = userId;
        this.courtId = courtId;
        this.date = date;
        this.hour = hour;
        this.status = status;
        
    }

    public void approve() {
        this.status = ReservationStatus.APPROVED;
        this.updatedAt = Instant.now();
    }

    public AggregateId userId() {
        return this.userId;
    }

    public AggregateId courtId() {
        return this.courtId;
    }

    public LocalDate date() {
        return this.date;
    }

    public int hour() {
        return this.hour;
    }

    public ReservationStatus status() {
        return this.status;
    }

    public Instant requestedAt() {
        return this.requestedAt;
    }

    public Instant updatedAt() {
        return this.updatedAt;
    }
}
