package gtu.cse.se.altefdirt.aymoose.facility.internal.application.command;

import java.util.List;

import gtu.cse.se.altefdirt.aymoose.shared.application.Command;
import jakarta.validation.constraints.NotBlank;

public record CreateFacility(
                @NotBlank(message = "Owner Id cannot be null or empty") String ownerId,
                @NotBlank(message = "Phone number cannot be null or empty") String phoneNumber,
                @NotBlank(message = "Facility name cannot be null or empty") String name,
                String description,
                @NotBlank(message = "Location cannot be null or empty") Long districtId,
                @NotBlank(message = "Location cannot be null or empty") String fullAddress,
                @NotBlank(message = "Location cannot be null or empty") String location,
                @NotBlank(message = "Contact details cannot be null or empty") String contactDetails,
                @NotBlank(message = "Open time cannot be null or empty") int openTime,
                @NotBlank(message = "Close time cannot be null or empty") int closeTime,
                List<String> amenities,
                boolean isActive) implements Command {
}
