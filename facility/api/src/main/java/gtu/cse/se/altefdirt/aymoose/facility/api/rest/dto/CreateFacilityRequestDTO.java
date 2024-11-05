package gtu.cse.se.altefdirt.aymoose.facility.api.rest.dto;

import java.util.List;
import org.apache.commons.lang3.Validate;

public record CreateFacilityRequestDTO(
        String userId,
        String facilityName,
        String facilityDescription,
        String phoneNumber,
        String location,
        String contactDetails,
        Integer courtCount,
        String city,
        String district,
        List<String> images
) {   
    public CreateFacilityRequestDTO (
        String userId,
        String facilityName,
        String facilityDescription,
        String phoneNumber,
        String location,
        String contactDetails,
        Integer courtCount,
        String city,
        String district,
        List<String> images
    ) {
        Validate.notNull(userId, "User ID cannot be null");
        Validate.notNull(facilityName, "Facility name cannot be null");
        Validate.notNull(facilityDescription, "Facility description cannot be null");
        Validate.notNull(phoneNumber, "Phone number cannot be null");
        Validate.notNull(location, "Location cannot be null");
        Validate.notNull(contactDetails, "Contact details cannot be null");
        Validate.notNull(courtCount, "Court count cannot be null");
        Validate.notNull(city, "City cannot be null");
        Validate.notNull(district, "District cannot be null");
        Validate.notNull(images, "Images cannot be null");

        Validate.isTrue(userId.length() == 36, "Invalid user ID");
        Validate.isTrue(facilityName.length() >= 3 && facilityName.length() <= 80, "Facility name must be between 3 and 80 characters");
        Validate.isTrue(facilityDescription.length() >= 3 && facilityDescription.length() <= 200, "Facility description must be between 3 and 200 characters");
        Validate.isTrue(phoneNumber.length() >= 10 && phoneNumber.length() <= 15, "Phone number must be between 10 and 15 characters");
        Validate.isTrue(location.length() >= 5 && location.length() <= 100, "Location must be between 5 and 100 characters");
        Validate.isTrue(contactDetails.length() >= 5 && contactDetails.length() <= 100, "Contact details must be between 5 and 100 characters");
        Validate.isTrue(courtCount >= 1 && courtCount <= 100, "Court count must be between 1 and 100");
        Validate.isTrue(city.length() >= 2 && city.length() <= 50, "City must be between 2 and 50 characters");
        Validate.isTrue(district.length() >= 2 && district.length() <= 50, "District must be between 2 and 50 characters");
        Validate.isTrue(images.size() >= 1 && images.size() <= 10, "Images must be between 1 and 10");

        this.userId = userId;
        this.facilityName = facilityName;
        this.facilityDescription = facilityDescription;
        this.phoneNumber = phoneNumber;
        this.location = location;
        this.contactDetails = contactDetails;
        this.courtCount = courtCount;
        this.city = city;
        this.district = district;
        this.images = images;
    }
}
