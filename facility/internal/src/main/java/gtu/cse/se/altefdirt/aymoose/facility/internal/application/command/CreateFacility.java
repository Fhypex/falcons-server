package gtu.cse.se.altefdirt.aymoose.facility.internal.application.command;

import java.util.List;

import gtu.cse.se.altefdirt.aymoose.shared.application.Command;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateFacility(
                @NotBlank(message = "Owner Id cannot be null or empty") String ownerId,
                @NotBlank(message = "Phone number cannot be null or empty") String phoneNumber,
                @NotBlank(message = "Facility name cannot be null or empty") String name,
                String description,
                @NotBlank(message = "Location cannot be null or empty") String city,
                @NotBlank(message = "Location cannot be null or empty") String district,
                @NotBlank(message = "Location cannot be null or empty") String fullAddress,
                @NotBlank(message = "Location cannot be null or empty") String location,
                @NotBlank(message = "Contact details cannot be null or empty") String contactDetails,
                boolean isActive) implements Command {
}
