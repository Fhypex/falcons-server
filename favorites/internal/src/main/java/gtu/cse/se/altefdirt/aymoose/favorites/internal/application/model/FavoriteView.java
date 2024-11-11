package gtu.cse.se.altefdirt.aymoose.favorites.internal.application.model;

import gtu.cse.se.altefdirt.aymoose.favorites.internal.domain.Favorites;
import lombok.Builder;

@Builder
public record FavoriteView (
    String id,
    String userId,
    String facilityId
) {
    public FavoriteView(Favorites favorites) {
        this(
            favorites.id().value(),
            favorites.userId().value(),
            favorites.facilityId().value()
        );
    }
}
