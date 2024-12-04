package gtu.cse.se.altefdirt.aymoose.reservation.internal.infra.adapter.rest.dto;

import java.time.LocalDate;

import org.apache.commons.lang3.Validate;

import jakarta.validation.constraints.Past;

public record DisableTimeSlotRequestDTO(String courtId, @Past LocalDate date, int hour) {
    public DisableTimeSlotRequestDTO(
        String courtId, LocalDate date, int hour) {
        Validate.notNull(date, "Date cannot be null");
        Validate.isTrue(hour >= 0 && hour <= 23, "Hour must be between 0 and 23");
        this.courtId = courtId;
        this.date = date;
        this.hour = hour;
    }
}
