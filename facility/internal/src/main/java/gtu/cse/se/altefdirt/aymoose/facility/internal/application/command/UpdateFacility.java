package gtu.cse.se.altefdirt.aymoose.facility.internal.application.command;

import gtu.cse.se.altefdirt.aymoose.shared.application.Command;
import jakarta.validation.constraints.NotBlank;

public record UpdateFacility(
                @NotBlank(message = "Facility Id cannot be null or empty") String id,

                @NotBlank(message = "Facility name cannot be null or empty") String facilityName,

                @NotBlank(message = "Facility description cannot be null or empty") String facilityDescription,

                String phoneNumber,

                Integer courtCount,

                String contactDetails,

                String location,

                String city,

                String district) implements Command {
}
