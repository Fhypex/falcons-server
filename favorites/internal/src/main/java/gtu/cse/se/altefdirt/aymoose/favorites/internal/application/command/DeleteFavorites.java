package gtu.cse.se.altefdirt.aymoose.favorites.internal.application.command;

import gtu.cse.se.altefdirt.aymoose.shared.application.Command;
import jakarta.validation.constraints.NotBlank;

public record DeleteFavorites(
        @NotBlank(message = "Favorite ID cannot be null or empty")
        String favoritesId
) implements Command {
}
