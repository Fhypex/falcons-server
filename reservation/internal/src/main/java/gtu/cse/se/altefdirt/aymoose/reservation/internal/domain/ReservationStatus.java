package gtu.cse.se.altefdirt.aymoose.reservation.internal.domain;

public enum ReservationStatus {
    PENDING,
    APPROVED,
    CANCELLED,
    REJECTED,
    COMPLETED,
    NO_SHOW;

    public static ReservationStatus fromString(String status) {
        for (ReservationStatus s : ReservationStatus.values()) {
            if (s.name().equalsIgnoreCase(status)) {
                return s;
            }
        }
        return null;
    }
}