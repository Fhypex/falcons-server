package gtu.cse.se.altefdirt.aymoose.court.api.rest.dto;

import org.apache.commons.lang3.Validate;

public record CreateCourtRequestDTO(
        String facilityId,
        String name,
        String description,
        Integer height,
        Integer width,
        Integer capacity) 
    {   
        
    public CreateCourtRequestDTO (
        String facilityId,
        String name,
        String description,
        Integer height,
        Integer width,
        Integer capacity) 
    {
        Validate.notNull(facilityId, "Facility ID cannot be null");
        Validate.notNull(name, "Court name cannot be null");
        Validate.notNull(description, "Court description cannot be null");
        Validate.notNull(height, "Court height cannot be null");
        Validate.notNull(width, "Court width cannot be null");
        Validate.notNull(capacity, "Court capacity cannot be null");
        Validate.isTrue(facilityId.length() == 36, "Invalid facility ID");
        Validate.isTrue(name.length() >= 3 && name.length() <= 80, "Court name must be between 3 and 80 characters");
        Validate.isTrue(description.length() >= 3 && description.length() <= 200, "Court description must be between 3 and 200 characters");
        Validate.isTrue(height >= 1 && height <= 100, "Court height must be between 1 and 100");
        Validate.isTrue(width >= 1 && width <= 100, "Court width must be between 1 and 100");
        Validate.isTrue(capacity >= 1 && capacity <= 100, "Court capacity must be between 1 and 100");
        this.facilityId = facilityId;
        this.name = name;
        this.description = description;
        this.height = height;
        this.width = width;
        this.capacity = capacity;
    }
}