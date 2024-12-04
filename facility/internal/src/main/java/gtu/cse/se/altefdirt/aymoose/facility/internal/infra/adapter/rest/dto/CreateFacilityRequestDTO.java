package gtu.cse.se.altefdirt.aymoose.facility.internal.infra.adapter.rest.dto;

import java.util.List;
import org.apache.commons.lang3.Validate;

public record CreateFacilityRequestDTO(
        String userId,
        String phoneNumber,
        String name,
        String description,
        Long districtId,
        String fullAddress,
        String location,
        String contactDetails,
        int openTime,
        int closeTime,
        List<String> amenityIds,
        Boolean isActive) {
    public CreateFacilityRequestDTO(
            String userId,
            String phoneNumber,
            String name,
            String description,
            Long districtId,
            String fullAddress,
            String location,
            String contactDetails,
            int openTime,
            int closeTime,
            List<String> amenityIds,
            Boolean isActive) {
        Validate.notNull(userId, "User ID cannot be null");
        Validate.notNull(phoneNumber, "Phone number cannot be null");
        Validate.notNull(name, "Facility name cannot be null");
        Validate.notNull(description, "Facility description cannot be null");
        Validate.notNull(districtId, "District cannot be null");
        Validate.notNull(fullAddress, "Full address cannot be null");
        Validate.notNull(location, "Location cannot be null");
        Validate.notNull(contactDetails, "Contact details cannot be null");

        Validate.isTrue(userId.length() == 36, "Invalid user ID");
        Validate.isTrue(name.length() >= 3 && name.length() <= 80, "Facility name must be between 3 and 80 characters");
        Validate.isTrue(description.length() >= 3 && description.length() <= 200,
                "Facility description must be between 3 and 200 characters");
        Validate.isTrue(phoneNumber.length() >= 10 && phoneNumber.length() <= 15,
                "Phone number must be between 10 and 15 characters");
        Validate.isTrue(fullAddress.length() >= 5 && fullAddress.length() <= 100,
                "Full address must be between 5 and 100 characters");
        Validate.isTrue(location.length() >= 5 && location.length() <= 100,
                "Location must be between 5 and 100 characters");
        Validate.isTrue(contactDetails.length() >= 5 && contactDetails.length() <= 100,
                "Contact details must be between 5 and 100 characters");

        Validate.isTrue(openTime >= 0 && openTime <= 24, "Invalid open time");
        Validate.isTrue(closeTime >= 0 && closeTime <= 24, "Invalid close time");
        Validate.isTrue(openTime < closeTime, "Open time must be before close time");
        Validate.isTrue(amenityIds.size() <= 10, "Maximum 10 amenities allowed");

        this.userId = userId;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.description = description;
        this.districtId = districtId;
        this.fullAddress = fullAddress;
        this.location = location;
        this.contactDetails = contactDetails;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.amenityIds = amenityIds;
        this.isActive = isActive;
    }
}
