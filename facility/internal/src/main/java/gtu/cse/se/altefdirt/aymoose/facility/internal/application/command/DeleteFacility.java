package gtu.cse.se.altefdirt.aymoose.facility.internal.application.command;

import java.util.UUID;

import gtu.cse.se.altefdirt.aymoose.shared.application.Command;

public record DeleteFacility(UUID id) implements Command {
}
