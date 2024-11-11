package gtu.cse.se.altefdirt.aymoose.facility.api.rest.dto;

import lombok.Builder;

@Builder
public record FacilityListResponseDTO(
    String id,
    String name,
    String image,
    int rating,
    String place
) {
    public static FacilityListResponseDTO from(Facility facility) {
        return FacilityListResponseDTO.builder()
            .id(facility.id().value())
            .name(facility.facilityName())
            .image("https://tesiskirala.com/wp-content/uploads/2024/01/IMG-20190515-WA0000.jpg")  
            .rating(3)  
            .place(facility.location().getCity() + "/" + facility.location().getDistrict())  
            .build();
    }
}
