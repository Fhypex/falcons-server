package gtu.cse.se.altefdirt.aymoose.facility.internal.application.command;

import java.util.UUID;

import gtu.cse.se.altefdirt.aymoose.shared.application.Command;

public record DeleteAmenity(UUID id) implements Command {
}
