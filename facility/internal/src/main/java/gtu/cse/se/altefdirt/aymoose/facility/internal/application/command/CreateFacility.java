package gtu.cse.se.altefdirt.aymoose.facility.internal.application.command;

import gtu.cse.se.altefdirt.aymoose.shared.application.Command;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateFacility(
        @NotBlank(message = "User Id cannot be null or empty")
        String userId,
        @NotBlank(message = "Facility name cannot be null or empty")
        String facilityName,
        @NotBlank(message = "Phone number cannot be null or empty")
        String phoneNumber,
        String facilityDescription,
        @NotBlank(message = "Location cannot be null or empty")
        Double latitude,
        Double longitude,
        @NotBlank(message = "Contact details cannot be null or empty")
        String contactDetails,
        @NotNull(message = "Court count cannot be null")
        Integer courtCount
) implements Command {
}
