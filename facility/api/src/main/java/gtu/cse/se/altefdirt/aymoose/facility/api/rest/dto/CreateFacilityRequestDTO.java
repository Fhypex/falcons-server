package gtu.cse.se.altefdirt.aymoose.facility.api.rest.dto;

import java.util.List;
import org.apache.commons.lang3.Validate;

public record CreateFacilityRequestDTO(
        String ownerId,
        String phoneNumber,
        String name,
        String description,
        String city,
        String district,
        String fullAddress,
        String location,
        String contactDetails,
        boolean isActive){
    public CreateFacilityRequestDTO(
        String ownerId,
        String phoneNumber,
        String name,
        String description,
        String city,
        String district,
        String fullAddress,
        String location,
        String contactDetails,
        boolean isActive) {
        Validate.notNull(ownerId, "User ID cannot be null");
        Validate.notNull(phoneNumber, "Phone number cannot be null");
        Validate.notNull(name, "Facility name cannot be null");
        Validate.notNull(description, "Facility description cannot be null");
        Validate.notNull(city, "City cannot be null");
        Validate.notNull(district, "District cannot be null");
        Validate.notNull(fullAddress, "Full address cannot be null");
        Validate.notNull(location, "Location cannot be null");
        Validate.notNull(contactDetails, "Contact details cannot be null");

        Validate.isTrue(ownerId.length() == 36, "Invalid user ID");
        Validate.isTrue(name.length() >= 3 && name.length() <= 80, "Facility name must be between 3 and 80 characters");
        Validate.isTrue(description.length() >= 3 && description.length() <= 200, "Facility description must be between 3 and 200 characters");
        Validate.isTrue(phoneNumber.length() >= 10 && phoneNumber.length() <= 15, "Phone number must be between 10 and 15 characters");
        Validate.isTrue(city.length() >= 2 && city.length() <= 50, "City must be between 2 and 50 characters");
        Validate.isTrue(district.length() >= 2 && district.length() <= 50, "District must be between 2 and 50 characters");
        Validate.isTrue(fullAddress.length() >= 5 && fullAddress.length() <= 100, "Full address must be between 5 and 100 characters");
        Validate.isTrue(location.length() >= 5 && location.length() <= 100, "Location must be between 5 and 100 characters");
        Validate.isTrue(contactDetails.length() >= 5 && contactDetails.length() <= 100, "Contact details must be between 5 and 100 characters");

        this.ownerId = ownerId;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.description = description;
        this.city = city;
        this.district = district;
        this.fullAddress = fullAddress;
        this.location = location;
        this.contactDetails = contactDetails;
        this.isActive = isActive;
    }
}
