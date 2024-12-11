package gtu.cse.se.altefdirt.aymoose.reservation.internal.application.command;

import java.time.LocalDate;
import java.util.UUID;

import gtu.cse.se.altefdirt.aymoose.shared.application.Command;

public record CreateClosedReservation(UUID courtId, LocalDate date, int hour) implements Command {
}
