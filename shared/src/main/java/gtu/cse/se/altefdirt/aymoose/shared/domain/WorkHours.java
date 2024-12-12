package gtu.cse.se.altefdirt.aymoose.shared.domain;

import java.util.List;
import java.util.ArrayList;

public record WorkHours(
        int openTime,
        int closeTime) {
    public WorkHours {
        if (openTime < 0 || openTime > 24) {
            throw new IllegalArgumentException("Invalid open time");
        }
        if (closeTime < 0 || closeTime > 24) {
            throw new IllegalArgumentException("Invalid close time");
        }
        if (openTime >= closeTime) {
            throw new IllegalArgumentException("Invalid work hours:" + openTime + " - " + closeTime);
        }
    }

    public String toString() {
        return String.format("%02d:00 - %02d:00", openTime, closeTime);
    }

    public int duration() {
        return closeTime - openTime;
    }

    public java.util.List<String> timeSlots() {
        List<String> timeSlots = new ArrayList<>(closeTime - openTime);
        for (int i = openTime; i < closeTime; i++) {
            timeSlots.add(String.format("%02d:00 - %02d:00", i, i + 1));
        }
        return timeSlots;
    }

    public boolean isWithin(int hour) {
        return hour >= openTime && hour < closeTime;
    }

}
