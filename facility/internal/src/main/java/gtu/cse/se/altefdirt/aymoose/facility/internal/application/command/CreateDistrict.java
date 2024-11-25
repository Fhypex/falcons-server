package gtu.cse.se.altefdirt.aymoose.facility.internal.application.command;

import gtu.cse.se.altefdirt.aymoose.shared.application.Command;

public record CreateDistrict(Long cityId, String name) implements Command {
}
