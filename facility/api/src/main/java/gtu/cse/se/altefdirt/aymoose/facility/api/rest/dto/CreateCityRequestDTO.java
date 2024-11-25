package gtu.cse.se.altefdirt.aymoose.facility.api.rest.dto;

import org.apache.commons.lang3.Validate;

public record CreateCityRequestDTO(String name){
    public CreateCityRequestDTO(
        String name) {
        Validate.notNull(name, "City name cannot be null");
        Validate.isTrue(name.length() >= 3 && name.length() <= 80, "City name must be between 3 and 80 characters");
        this.name = name;
    }
}
