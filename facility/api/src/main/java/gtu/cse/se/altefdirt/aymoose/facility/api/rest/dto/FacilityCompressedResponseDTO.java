package gtu.cse.se.altefdirt.aymoose.facility.api.rest.dto;


import java.util.List;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.model.FacilityView;
import lombok.Builder;

@Builder
public record FacilityResponseDTO(
    String id,
    String facilityName,
    String facilityDescription,
    String phoneNumber,
    String contactDetails,
    Integer courtCount,
    Double latitude,
    Double longitude,
    String images
) {
    public static FacilityResponseDTO fromView(FacilityView facilityView) {
        return FacilityResponseDTO.builder()
            .id(facilityView.id())
            .facilityName(facilityView.facilityName())
            .facilityDescription(facilityView.facilityDescription())
            .phoneNumber(facilityView.phoneNumber())
            .contactDetails(facilityView.contactDetails())
            .courtCount(facilityView.courtCount())
            .latitude(facilityView.location().latitude())
            .longitude(facilityView.location().longitude())
            .images(facilityView.images())
            .build();
    }
}