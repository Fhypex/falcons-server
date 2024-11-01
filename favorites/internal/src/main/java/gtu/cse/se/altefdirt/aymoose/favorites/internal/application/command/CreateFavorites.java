package gtu.cse.se.altefdirt.aymoose.favorites.internal.application.command;

import gtu.cse.se.altefdirt.aymoose.shared.application.Command;
import jakarta.validation.constraints.NotBlank;

public record CreateFavorites(
        @NotBlank(message = "User ID cannot be null or empty")
        String userId,
        
        @NotBlank(message = "Facility ID cannot be null or empty")
        String facilityId
) implements Command {
}
