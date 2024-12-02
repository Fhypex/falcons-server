package gtu.cse.se.altefdirt.aymoose.reservation.internal.application.command;

import java.time.LocalDate;

import gtu.cse.se.altefdirt.aymoose.shared.application.Command;

public record DisabledTimeSlot(String courtId, LocalDate date, int hour) implements Command {
}
