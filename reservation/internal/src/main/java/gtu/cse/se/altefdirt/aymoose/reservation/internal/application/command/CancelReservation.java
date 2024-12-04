package gtu.cse.se.altefdirt.aymoose.reservation.internal.application.command;

import gtu.cse.se.altefdirt.aymoose.shared.application.Command;
import gtu.cse.se.altefdirt.aymoose.shared.domain.AggregateId;

public record CancelReservation(String id) implements Command {
}
