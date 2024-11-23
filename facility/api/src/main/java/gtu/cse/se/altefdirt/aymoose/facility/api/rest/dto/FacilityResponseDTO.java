package gtu.cse.se.altefdirt.aymoose.facility.api.rest.dto;

import java.util.List;
import gtu.cse.se.altefdirt.aymoose.facility.internal.application.model.FacilityView;
import lombok.Builder;

@Builder
public record FacilityResponseDTO(
        String id,
        String phoneNumber,
        String name,
        String description,
        String city,
        String district,
        String fullAddress,
        String location,
        String imageUrl,
        String contactDetails,
        List<String> images) {
    public static FacilityResponseDTO fromView(FacilityView facilityView) {
        return FacilityResponseDTO.builder()
                .id(facilityView.id())
                .phoneNumber(facilityView.phoneNumber())
                .name(facilityView.name())
                .description(facilityView.description())
                .city(facilityView.city())
                .district(facilityView.district())
                .fullAddress(facilityView.fullAddress())
                .location(facilityView.location())
                .contactDetails(facilityView.contactDetails())
                .imageUrl(facilityView.imageUrl())
                .build();
    }
}
