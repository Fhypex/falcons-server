package gtu.cse.se.altefdirt.aymoose.reservation.internal.application.command;

import java.util.UUID;
import gtu.cse.se.altefdirt.aymoose.shared.application.Command;
import gtu.cse.se.altefdirt.aymoose.shared.domain.Date;

public record CreateClosedReservation(UUID courtId, Date date, int hour) implements Command {
}
