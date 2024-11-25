package gtu.cse.se.altefdirt.aymoose.facility.api.rest.dto;

import org.apache.commons.lang3.Validate;

public record CreateDistrictRequestDTO(Long cityId, String name){
    public CreateDistrictRequestDTO(Long cityId,
        String name) {
        Validate.notNull(name, "District name cannot be null");
        Validate.isTrue(name.length() >= 3 && name.length() <= 80, "District name must be between 3 and 80 characters");
        this.name = name;
        this.cityId = cityId;
    }
}
