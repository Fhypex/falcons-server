package gtu.cse.se.altefdirt.aymoose.reservation.internal.application.command;

import gtu.cse.se.altefdirt.aymoose.shared.application.Command;

public record DeleteClosedReservation(Long reservationId) implements Command {
}
