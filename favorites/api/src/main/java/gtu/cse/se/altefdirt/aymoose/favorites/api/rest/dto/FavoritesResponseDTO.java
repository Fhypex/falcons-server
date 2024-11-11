package gtu.cse.se.altefdirt.aymoose.favorites.api.rest.dto;

import gtu.cse.se.altefdirt.aymoose.favorites.internal.application.model.FavoritesView;
import lombok.Builder;

@Builder
public record FavoritesResponseDTO(
    String id,
    String userId,
    String facilityId
) {
    public static FavoritesResponseDTO fromView(FavoritesView favoritesView) {
        return FavoritesResponseDTO.builder()
            .id(favoritesView.id())
            .userId(favoritesView.userId())
            .facilityId(favoritesView.facilityId())
            .build();
    }
}
