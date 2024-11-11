package gtu.cse.se.altefdirt.aymoose.facility.api.rest.dto;

import java.util.List;
import org.apache.commons.lang3.Validate;

public record CreateFacilityRequestDTO(
        String userId,
        String facilityName,
        String facilityDescription,
        String phoneNumber,
        Double latitude,
        Double longitude,
        String contactDetails,
        Integer courtCount,
        List<String> images
) {   
    public CreateFacilityRequestDTO (
        String userId,
        String facilityName,
        String facilityDescription,
        String phoneNumber,
        Double latitude,
        Double longitude,
        String contactDetails,
        Integer courtCount,
        List<String> images
    ) {
        Validate.notNull(userId, "User ID cannot be null");
        Validate.notNull(facilityName, "Facility name cannot be null");
        Validate.notNull(facilityDescription, "Facility description cannot be null");
        Validate.notNull(phoneNumber, "Phone number cannot be null");
        Validate.notNull(latitude, "Facility latitude cannot be null");
        Validate.notNull(longitude, "Facility longitude cannot be null");
        Validate.notNull(contactDetails, "Contact details cannot be null");
        Validate.notNull(courtCount, "Court count cannot be null");
        Validate.notNull(images, "Images cannot be null");
        Validate.isTrue(userId.length() == 36, "Invalid user ID");
        Validate.isTrue(facilityName.length() >= 3 && facilityName.length() <= 80, "Facility name must be between 3 and 80 characters");
        Validate.isTrue(facilityDescription.length() >= 3 && facilityDescription.length() <= 200, "Facility description must be between 3 and 200 characters");
        Validate.isTrue(phoneNumber.length() >= 10 && phoneNumber.length() <= 15, "Phone number must be between 10 and 15 characters");
        Validate.isTrue(latitude >= -90 && latitude <= 90, "Latitude must be between -90 and 90");
        Validate.isTrue(longitude >= -180 && longitude <= 180, "Longitude must be between -180 and 180");
        Validate.isTrue(contactDetails.length() >= 5 && contactDetails.length() <= 100, "Contact details must be between 5 and 100 characters");
        Validate.isTrue(courtCount >= 1 && courtCount <= 100, "Court count must be between 1 and 100");
        Validate.isTrue(images.size() >= 1 && images.size() <= 10, "Images must be between 1 and 10");

        this.userId = userId;
        this.facilityName = facilityName;
        this.facilityDescription = facilityDescription;
        this.phoneNumber = phoneNumber;
        this.latitude = latitude;
        this.longitude = longitude;
        this.contactDetails = contactDetails;
        this.courtCount = courtCount;
        this.images = images;
    }
}
