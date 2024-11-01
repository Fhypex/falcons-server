package gtu.cse.se.altefdirt.aymoose.favorites.api.rest.dto;

import org.apache.commons.lang3.Validate;

public record CreateFavoritesRequestDTO(
        String userId,
        String facilityId
) {   
    public CreateFavoritesRequestDTO(String userId, String facilityId) {
        Validate.notNull(userId, "User ID cannot be null");
        Validate.notNull(facilityId, "Facility ID cannot be null");
        Validate.isTrue(userId.length() == 36, "Invalid user ID format");
        Validate.isTrue(facilityId.length() == 36, "Invalid facility ID format");

        this.userId = userId;
        this.facilityId = facilityId;
    }
}
