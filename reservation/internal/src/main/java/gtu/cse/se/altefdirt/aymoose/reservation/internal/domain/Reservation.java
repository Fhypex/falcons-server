package gtu.cse.se.altefdirt.aymoose.reservation.internal.domain;

import lombok.Getter;
import java.time.Instant;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;
import gtu.cse.se.altefdirt.aymoose.shared.domain.BaseAggregateRoot;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Date;

@Getter
public class Reservation extends BaseAggregateRoot implements Reservable {

    private static final ReservationType type = ReservationType.DEFAULT;
    private AggregateId userId;
    private AggregateId ownerId;
    private AggregateId courtId;
    private Date date;
    private int hour;
    private ReservationStatus status;
    private Instant requestedAt;
    private Instant updatedAt;

    public Reservation(AggregateId id,
            AggregateId userId,
            AggregateId ownerId,
            AggregateId courtId,
            Date date,
            int hour,
            ReservationStatus status,
            Instant requestedAt,
            Instant updatedAt) {
        super(id);
        this.userId = userId;
        this.ownerId = ownerId;
        this.courtId = courtId;
        this.date = date;
        this.hour = hour;
        this.status = status;
        this.requestedAt = requestedAt;
        this.updatedAt = updatedAt;
    }

    public void approve() {
        this.status = ReservationStatus.APPROVED;
        this.updatedAt = Instant.now();
    }

    public void reject() {
        this.status = ReservationStatus.REJECTED;
        this.updatedAt = Instant.now();
    }

    public void cancel() {
        this.status = ReservationStatus.CANCELLED;
        this.updatedAt = Instant.now();
    }

    public void complete() {
        this.status = ReservationStatus.COMPLETED;
        this.updatedAt = Instant.now();
    }

    public AggregateId userId() {
        return this.userId;
    }

    public AggregateId ownerId() {
        return this.ownerId;
    }

    public AggregateId courtId() {
        return this.courtId;
    }

    public Date date() {
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

    @Override
    public ReservationType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id() +
                "userId=" + userId +
                ", courtId=" + courtId +
                ", date=" + date +
                ", hour=" + hour +
                ", status=" + status +
                ", requestedAt=" + requestedAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
