package gtu.cse.se.altefdirt.aymoose.reservation.internal.application.command;

import java.util.UUID;
import gtu.cse.se.altefdirt.aymoose.shared.application.Command;

public record DeleteClosedReservation(UUID id) implements Command {
}
