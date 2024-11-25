package gtu.cse.se.altefdirt.aymoose.facility.api.rest.dto;

import org.apache.commons.lang3.Validate;
import org.springframework.web.multipart.MultipartFile;

public record CreateAmenityRequestDTO(String name){
    public CreateAmenityRequestDTO(
        String name) {

        Validate.notNull(name, "Amenity name cannot be null");

        Validate.isTrue(name.length() >= 3 && name.length() <= 80, "Amenity name must be between 3 and 80 characters");
        this.name = name;
    }
}
